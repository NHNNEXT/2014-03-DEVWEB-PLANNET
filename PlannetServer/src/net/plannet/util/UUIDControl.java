package net.plannet.util;
import java.util.UUID;

public class UUIDControl {
	public String createUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
