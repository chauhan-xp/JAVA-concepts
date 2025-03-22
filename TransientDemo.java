package repo_1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class user implements Serializable{
	private String username;
	private transient String password;
	
	public user(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "user [username=" + username + ", password=" + password + "]";
	}
}

public class TransientDemo {
	public static void main(String[] args) {
		user u = new user("John", "John@123");
		System.out.println("Before Serialization : " + u);
		
		byte[] serializeUser = serialize(u);
		user deseializeUser = deserialize(serializeUser);
		System.out.println("After Deserialization : " + deseializeUser);
	}

	public static user deserialize(byte [] serializeUser) {
		// TODO Auto-generated method stub
		try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(serializeUser);
	             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
	            return (user) objectInputStream.readObject();
	        } catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
		return null;
	}

	public static byte[] serialize(user u) {
		// TODO Auto-generated method stub
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
	            objectOutputStream.writeObject(u);
	            return byteArrayOutputStream.toByteArray();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		return null;
	}
}
