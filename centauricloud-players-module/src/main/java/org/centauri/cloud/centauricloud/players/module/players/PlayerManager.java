package org.centauri.cloud.centauricloud.players.module.players;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class PlayerManager {

	private final Set<Player> players = new HashSet<>();
	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();//Should we use the fairness flag?
	
	public void remove(Player player) {
		this.lock.writeLock().lock();
		
		try {
			this.players.remove(player);
		} finally {
			this.lock.writeLock().unlock();
		}
	}

	public void remove(UUID uniqueId) {
		this.lock.writeLock().lock();
		
		try {
			this.players.removeIf(player -> player.getUniqueId().equals(uniqueId));
		} finally {
			this.lock.writeLock().unlock();
		}
	}

	public void add(Player player) {
		this.lock.writeLock().lock();
		
		try {
			this.players.add(player);
		} finally {
			this.lock.writeLock().unlock();
		}
	}
	
	/**
	 * Used for synchronized streaming over the players set.
	 * 
	 * If you want to delete something from the players set than use #streamWriting !
	 * 
	 * @param consumer that accepts and handles a synchronized stream 
	 */
	public void streamReading(Consumer<Stream> consumer) {
		this.lock.readLock().lock();
		
		try {
			consumer.accept(this.players.stream());
		} finally {
			this.lock.readLock().unlock();
		}
	}
	
	/**
	 * Used for synchronized streaming over the players set.
	 * 
	 * @param consumer that accepts and handles a synchronized stream 
	 */
	public void streamWriting(Consumer<Stream> consumer) {
		this.lock.writeLock().lock();
		
		try {
			consumer.accept(this.players.stream());
		} finally {
			this.lock.writeLock().unlock();
		}
	}
	
	public Player getPlayer(UUID uniqueId) {
		this.lock.readLock().lock();
		
		try {
			return this.players.stream().filter(player -> player.getUniqueId().equals(uniqueId)).findFirst().get();
		} finally {
			this.lock.readLock().unlock();
		}
	}

	public Player getPlayer(String name) {
		this.lock.readLock().lock();
		
		try {
			return this.players.stream().filter(player -> player.getName().equalsIgnoreCase(name)).findFirst().get();
		} finally {
			this.lock.readLock().unlock();
		}
	}

}