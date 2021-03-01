package page.zhaoyuan.gds.main;

public class Transaction {
    private String payer;
    private String payee;
    private double amount;

    public Transaction(String payer, String payee, double amount) {
        this.payer = payer;
        this.payee = payee;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return this.payer + " pays " + this.payee + " $" + this.amount;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Transaction) && obj.toString().equals(this.toString());
    }
}
