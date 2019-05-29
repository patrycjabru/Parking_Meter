package service.local;

import service.MessageStorage;

import javax.ejb.Local;

@Local
public interface MessageStorageLocal extends MessageStorage {
}
