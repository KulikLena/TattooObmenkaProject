package graduateprojectpackage;

public class TestLoginParametr {
	public String login = "";
	public String passwword = "";
	public String result = "";

	public TestLoginParametr(String str) {
		String[] st = str.split(",");
		switch (st[1]) {
		case "1": {
			this.result = st[0];
			this.login = st[2];
			this.passwword = st[3];
		}
			break;
		case "2": {
			this.result = st[0];
			this.login = "";
			this.passwword = st[3];
		}
			break;
		case "3": {
			this.result = st[0];
			this.login = st[2];
			this.passwword = "";

		}
			break;
		case "4": {
			this.result = st[0];
			this.login = "";
			this.passwword = "";
		}
			break;
		default: {
			this.login = Parametrs“.emmail;
			this.passwword = Parametrs“.password;

		}
		}

	}
}
