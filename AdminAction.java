package roleActions;

public class AdminAction implements PersonAction {
	@Override
	public void performAction() {
		System.out.println("Admin action performed");
	}
}
