package roleActions;

public class UserAction implements PersonAction{
	@Override
	public void performAction() {
		System.out.println("User action performed");
	}
}
