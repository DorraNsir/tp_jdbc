package maprojetdao;

import java.util.List;

public interface IDao<Client> {
    boolean create(Client c);

    boolean delete(Client c);

    boolean update(Client c);

    Client findById(int id);

    List<Client> findAll();
}
