package pjb.aromabar;

public class Main {
	public static void main(String[] args) {
		String[] aromes = new String[] { "Ananas", "Poire",
				"Groseille à maquereau", "Abricot", "Miel", "Pêche", "Banane",
				"Pomme", "Orange", "Pamplemousse", "Figue", "Citron",
				"Fruits des bois", "Fraise", "Prune", "Framboise", "Cassis",
				"Cerise", "Mûre", "Poivre", "Violette", "Amande",
				"Poivron vert", "Menthe", "Chocolat amer", "Café", "Cuir",
				"Cèdre", "Beurre", "Caramel", "Truffe", "Champignon",
				"Note fumée", "Vanille", "Réglisse", "Tabac", "Bouchon",
				"Note de pétrole", "Note atypique de maturation", "Oignon",
				"Acide lactique", "Livèche", "Vinaigre", "Colle",
				"Sueur de cheval", "Souffre", "Végétal", "Chou-fleur", "Rose",
				"Levure", "Noix de muscade", "Pâte d'amande", "Eucalyptus",
				"Raisin sec", "Litchi", "Melon", "Fleur de sureau", "Mangue",
				"Myrtille", "Mirabelle" };

		String[] categories = new String[] { "Vin blanc     ",
				"Vin rouge     ", "Fût de chêne  ", "Défauts du vin",
				"Spécialités   " };

		int vinBlanc = UserSettings.getInstance().getIntegerValue("vin.blanc",
				2); // categorie=0
		int vinRouge = UserSettings.getInstance().getIntegerValue("vin.rouge",
				2); // categorie=1
		int futDeChene = UserSettings.getInstance().getIntegerValue(
				"fut.de.chene", 2); // categorie=2
		int defautsDuVin = UserSettings.getInstance().getIntegerValue(
				"defauts.du.vin", 2); // categorie=3
		int specialites = UserSettings.getInstance().getIntegerValue(
				"specialites", 2); // categorie=4

		int[] vb = getNumbers(vinBlanc, 0);
		int[] vr = getNumbers(vinRouge, 1);
		int[] fdc = getNumbers(futDeChene, 2);
		int[] ddv = getNumbers(defautsDuVin, 3);
		int[] s = getNumbers(specialites, 4);

		int[] mix1 = mix(vb, vr, fdc, ddv, s);
		int[] mix2 = mix(vb, vr, fdc, ddv, s);
		
		System.out.println("-----");
		for(int i=0 ; i<mix1.length ; ++i) {
			System.out.println(mix1[i]+1);
		}
		System.out.println("-----");
		for(int i=0 ; i<mix1.length ; ++i) {
			System.out.println(aromes[mix2[i]]);
		}
		System.out.println("-----");
	}

	private static int[] mix(int[] vb, int[] vr, int[] fdc, int[] ddv, int[] s) {
		int[] indices = getIndices(vb.length + vr.length + fdc.length
				+ ddv.length + s.length);
		int[] mix = new int[vb.length + vr.length + fdc.length + ddv.length
				+ s.length];
		int[] concat = concat(vb, vr, fdc, ddv, s);
		for (int i = 0; i < mix.length; ++i) {
			mix[i] = concat[indices[i]];
		}
		return mix;
	}

	private static int[] concat(int[] vb, int[] vr, int[] fdc, int[] ddv,
			int[] s) {
		int[] concat = new int[vb.length + vr.length + fdc.length + ddv.length
				+ s.length];
		for (int i = 0; i < vb.length; ++i) {
			concat[i] = vb[i];
		}
		for (int i = 0; i < vr.length; ++i) {
			concat[vb.length + i] = vr[i];
		}
		for (int i = 0; i < fdc.length; ++i) {
			concat[vb.length + vr.length + i] = fdc[i];
		}
		for (int i = 0; i < ddv.length; ++i) {
			concat[vb.length + vr.length + fdc.length + i] = ddv[i];
		}
		for (int i = 0; i < s.length; ++i) {
			concat[vb.length + vr.length + fdc.length + ddv.length + i] = s[i];
		}
		return concat;
	}

	private static int[] getNumbers(int n, int categorie) {
		int[] numbers = new int[n];
		int v;
		set(numbers, -1);
		for (int i = 0; i < numbers.length; ++i) {
			do {
				v = (int) (Math.random() * 12) + 12 * categorie;
			} while (contains(numbers, v));
			numbers[i] = v;
		}
		return numbers;
	}
	
	private static int[] getIndices(int n) {
		int[] numbers = new int[n];
		int v;
		set(numbers, -1);
		for (int i = 0; i < numbers.length; ++i) {
			do {
				v = (int) (Math.random() * n);
			} while (contains(numbers, v));
			numbers[i] = v;
		}
		return numbers;
	}

	private static void set(int[] tab, int n) {
		for (int i = 0; i < tab.length; ++i) {
			tab[i] = n;
		}
	}

	private static boolean contains(int[] tab, int n) {
		for (int i = 0; i < tab.length; ++i) {
			if (tab[i] == n) {
				return true;
			}
		}
		return false;
	}

}
