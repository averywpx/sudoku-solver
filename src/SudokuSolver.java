import controller.Controller;
import controller.ControllerImpl;
import model.SudokuSolverModel;
import model.SudokuSolverModelImpl;
import view.SudokuSolverView;
import view.SudokuSolverViewImpl;

public class SudokuSolver {
  public static void main(String[] args) {
    SudokuSolverModelImpl test = new SudokuSolverModelImpl();
    SudokuSolverModel model = new SudokuSolverModelImpl();
    SudokuSolverView view = new SudokuSolverViewImpl("Echo a string");
    Controller controller = new ControllerImpl(model, view);
  }

}

//007500816002160490196804000573601000260040105000025638349008060000473509720010084
