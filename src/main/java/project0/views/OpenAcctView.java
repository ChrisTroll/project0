package project0.views;

import project0.acctobjects.User;

public class OpenAcctView implements View{
	private User acctowner;

	public OpenAcctView(User user) {
		this.acctowner = user;
	}

	@Override
	public View process() {
		
		return new UserMenu(acctowner);
	}

}
