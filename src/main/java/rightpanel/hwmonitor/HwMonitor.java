package rightpanel.hwmonitor;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import share.StdThings;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.NetworkIF;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class HwMonitor extends JPanel {
    private final SystemInfo si = new SystemInfo();
    private final CentralProcessor processor = si.getHardware().getProcessor();
    private long[] prevTicks = new long[CentralProcessor.TickType.values().length];
    private Gauge Pcpu = new Gauge("cpu");
    private Gauge Pram = new Gauge("ram");
    private Gauge Pswap = new Gauge("swap");
    private Gauge Pdisk = new Gauge("disk");
    private Gauge Pnet = new Gauge("net");
    private long lastDiskBytes = 0;
    private long lastNetBytes = 0;
    private long lastTimestamp = 0;
    public HwMonitor(){
        this.setPreferredSize(new Dimension(280,86));
        this.setBackground(StdThings.getColor1());
        this.setBorder(new EmptyBorder(new Insets(3, 3, 3, 3)));        
        this.setLayout(new GridLayout(0,5));
        this.add(Pcpu);
        this.add(Pram);
        this.add(Pswap);
        this.add(Pdisk);
        this.add(Pnet);
        start();
    }
    public void start() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(); //equivalent of setInterval in JS
        scheduler.scheduleAtFixedRate(this::displayStats, 0, 1, TimeUnit.SECONDS);
    }
    private void displayStats() {
        long currentTimestamp = System.currentTimeMillis();
        // --- CPU ---
        double cpu = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100;
        prevTicks = processor.getSystemCpuLoadTicks();
        int actuallyCpu = (int)(Math.round(cpu));
        Pcpu.setPercentage(actuallyCpu);

        // --- RAM & SWAP ---
        GlobalMemory mem = si.getHardware().getMemory();
        double ram = ((double)(mem.getTotal() - mem.getAvailable()) / mem.getTotal()) * 100;
        double swap = ((double)mem.getVirtualMemory().getSwapUsed() / mem.getVirtualMemory().getSwapTotal()) * 100;
        int actuallyRam = (int)(Math.round(ram));
        int actuallySwap = (int)(Math.round(swap));
        Pram.setPercentage(actuallyRam);
        Pswap.setPercentage(actuallySwap);

        // --- DISk ---
        List<HWDiskStore> disks = si.getHardware().getDiskStores();
        long currentDiskBytes = 0;
        for (HWDiskStore d : disks) {
            d.updateAttributes();
            currentDiskBytes += (d.getReadBytes() + d.getWriteBytes());
        }
        // ---NET---
        List<NetworkIF> nets = si.getHardware().getNetworkIFs();
        long currentNetBytes = 0;
        for (NetworkIF n : nets) {
            n.updateAttributes();
            currentNetBytes += (n.getBytesRecv() + n.getBytesSent());
        }
        if (lastTimestamp != 0) {
            double elapsedSeconds = (currentTimestamp - lastTimestamp) / 1000.0;
            double diffDisk = (currentDiskBytes - lastDiskBytes) / (1024.0 * 1024.0);
            double mbps = diffDisk / elapsedSeconds; //needed
            int actuallyDisk = (int)(Math.round(mbps));
            int diskInt = (int) Math.min(100, Math.max(0, (mbps / 4.0) * 100)); //needed
            Pdisk.setPercentage(actuallyDisk, diskInt, "mb/s");
            double diffNet = (currentNetBytes - lastNetBytes) / 1024.0;
            double kbps = diffNet / elapsedSeconds; //needed
            int actuallyNet = (int)(Math.round(kbps));
            int netInt = (int) Math.min(100, Math.max(0, (kbps / 4.0) * 100)); //needed
            Pnet.setPercentage(actuallyNet, netInt, "kb/s");
        }
        lastDiskBytes = currentDiskBytes;
        lastNetBytes = currentNetBytes;
        lastTimestamp = currentTimestamp;
    }
}
