package page.zhaoyuan.gds.main;

public class Record implements Comparable<Record> {
    private String name;

    private double amount;

    public Record(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public int compareTo(Record record) {
        return Double.compare(this.amount, record.amount);
    }
}
