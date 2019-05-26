package service.local;

import service.DatabaseController;

import javax.ejb.Local;

@Local
public interface DatabaseControllerLocal extends DatabaseController {
}
