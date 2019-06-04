package Repository;

import Model.ProgramState;

public interface IRepo {
    void logPrgStateExec(ProgramState ps) throws RepoException;
}
