
abstract public class Worker {
	private String name;
	private double salary_rate;
	public String getName() {
		return name;
	}
	public double getSalary_rate() {
		return salary_rate;
	}
	public Worker(String name, double salary_rate) {
		this.name = name;
		this.salary_rate = salary_rate;
	}
	abstract public double computePay();
}
