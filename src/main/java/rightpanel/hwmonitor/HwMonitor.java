package rightpanel.hwmonitor;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import share.StdThings;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
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
    public HwMonitor(){
        this.setPreferredSize(new Dimension(280,86));
        this.setBackground(StdThings.getColor1());
        this.setBorder(new EmptyBorder(new Insets(3, 3, 3, 3)));        
        this.setLayout(new GridLayout(0,5));
        this.add(Pcpu);
        this.add(Pram);
        this.add(Pdisk);
        this.add(Pswap);
        this.add(Pnet);
        start();
    }
    public void start() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(); //equivalent of setInterval in JS
        scheduler.scheduleAtFixedRate(this::displayStats, 0, 1, TimeUnit.SECONDS);
    }
    private void displayStats() {
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

        // --- DISCO ---
        var fs = si.getOperatingSystem().getFileSystem().getFileStores().get(0);
        double disk = ((double)(fs.getTotalSpace() - fs.getUsableSpace()) / fs.getTotalSpace()) * 100;
        int actuallyDisk = (int)(Math.round(disk));
        Pdisk.setPercentage(actuallyDisk);

        // --- RETE (Esempio sommario) ---
        List<NetworkIF> networkIFs = si.getHardware().getNetworkIFs();
        long inviati = networkIFs.stream().mapToLong(NetworkIF::getBytesSent).sum();
        long ricevuti = networkIFs.stream().mapToLong(NetworkIF::getBytesRecv).sum();
        
        // Nota: per la rete conviene calcolare la differenza tra due secondi per avere i KB/s
        
    }


}
