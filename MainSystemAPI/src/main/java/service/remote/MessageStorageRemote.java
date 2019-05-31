package service.remote;

import service.MessageStorage;

import javax.ejb.Remote;

@Remote
public interface MessageStorageRemote extends MessageStorage {
}