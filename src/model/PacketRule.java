package model;
public class PacketRule implements Comparable<PacketRule> {
    private final int id;
    private final String sourceIP;
    private final String destinationIP;
    private final int priority;

    public PacketRule(int id, String sourceIP, String destinationIP, int priority) {
        if (sourceIP == null || destinationIP == null) {
            throw new IllegalArgumentException("IPs não podem ser nulos.");
        }
        if (priority < 0) {
            throw new IllegalArgumentException("Prioridade não pode ser negativa.");
        }
        this.id = id;
        this.sourceIP = sourceIP;
        this.destinationIP = destinationIP;
        this.priority = priority;
    }

    @Override
    public int compareTo(PacketRule other) {
        if (this.priority != other.priority) {
            return Integer.compare(this.priority, other.priority);
        }
        return Integer.compare(this.id, other.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PacketRule)) return false;
        PacketRule other = (PacketRule) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    public int getId() {
        return id;
    }

    public String getSourceIP() {
        return sourceIP;
    }

    public String getDestinationIP() {
        return destinationIP;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return String.format(
                "PacketRule[id=%d | %s → %s | priority=%d]",
                id, sourceIP, destinationIP, priority
        );
    }

    public static PacketRule generate(int id, java.util.Random rand) {
        String srcIP  = randomIP(rand);
        String dstIP  = randomIP(rand);
        int priority  = rand.nextInt(100_000); // 0 a 99.999
        return new PacketRule(id, srcIP, dstIP, priority);
    }

    private static String randomIP(java.util.Random rand) {
        return rand.nextInt(256) + "." +
                rand.nextInt(256) + "." +
                rand.nextInt(256) + "." +
                rand.nextInt(256);
    }
}