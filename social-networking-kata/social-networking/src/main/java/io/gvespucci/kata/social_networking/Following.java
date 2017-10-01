package io.gvespucci.kata.social_networking;

public class Following {

	private final String followerName;
	private final String followeeName;

	public Following(String followerName, String followeeName) {
		this.followerName = followerName;
		this.followeeName = followeeName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.followeeName == null ? 0 : this.followeeName.hashCode());
		result = prime * result + (this.followerName == null ? 0 : this.followerName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Following)) {
			return false;
		}
		final Following other = (Following) obj;
		if (this.followeeName == null) {
			if (other.followeeName != null) {
				return false;
			}
		} else if (!this.followeeName.equals(other.followeeName)) {
			return false;
		}
		if (this.followerName == null) {
			if (other.followerName != null) {
				return false;
			}
		} else if (!this.followerName.equals(other.followerName)) {
			return false;
		}
		return true;
	}

	public String follower() {
		return this.followerName;
	}

	public String followee() {
		return this.followeeName;
	}

	@Override
	public String toString() {
		return this.followerName + " follows " + this.followeeName;
	}

}
