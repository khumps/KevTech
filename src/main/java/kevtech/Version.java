package kevtech;

public class Version implements Comparable {
	public final int major;
	public final int minor;
	public final int patch;

	public Version(int major, int minor, int patch) {
		this.major = major;
		this.minor = minor;
		this.patch = patch;
	}

	@Override
	public int compareTo(Object object) {
		if (object instanceof Version) {
			Version v = (Version) object;
			if (major > v.major)
				return 1;
			if (v.major > major)
				return -1;
			if (minor > v.minor)
				return 1;
			if (v.minor > minor)
				return -1;
			if (patch > v.patch)
				return 1;
			if (v.patch > patch)
				return -1;
			return 0;
		}
		return 0;
	}

	public String toString() {
		return major + "." + minor + "." + patch;
	}
}
