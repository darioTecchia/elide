package elide.main;

import java.io.IOException;

import elide.model.structure.Structure;
// import elide.model.structure.StructureMainView;
import elide.model.user.User;
import elide.model.user.User.ClientKindEnum;

public class Main {

  @SuppressWarnings("unused")
  public static void main(String[] args) throws ClassNotFoundException, IOException {

    User structureAdmin = new User("Admin", "admin");
    User user1 = new User("Dario", "dario", ClientKindEnum.STUDENT);
    User user2 = new User("Diego", "diego", ClientKindEnum.ELDERLY);
    User user3 = new User("Macco", "macco", ClientKindEnum.KIDS);

    Structure structure = new Structure("Nike");
    structure.getRegistreredUsers().add(structureAdmin);
    structure.getRegistreredUsers().add(user1);
    structure.getRegistreredUsers().add(user2);
    structure.getRegistreredUsers().add(user3);

    // StructureMainView mainView = new StructureMainView(structure);
  }

}
