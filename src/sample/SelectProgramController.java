package sample;


import Model.Expression.ArithmExpression;
import Model.Expression.ConstExpression;
import Model.Expression.VarExpression;
import Model.Statements.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SelectProgramController {

    @FXML
    private ListView<String> listView;

    @FXML
    private Button runProgramBTN;

    public static StmtInterface statement;
    private List<StmtInterface> StmtList = new ArrayList<StmtInterface>();


    @FXML
    public void initialize() {

        //v=0;
        // (repeat (fork(print(v);v=v-1);v=v+1) until v==3);
        // x=1;y=2;z=3;w=4;
        // print(v*10)
        // The final Out should be {0,1,2,30}
        StmtInterface s1 = new AssignStmt("v", new ConstExpression(0));
        StmtInterface s2 = new PrintStmt(new VarExpression("v"));
        StmtInterface s3 = new AssignStmt("v", new ArithmExpression("+", new VarExpression("v"), new ConstExpression(1)));
        StmtInterface s4 = new AssignStmt("v", new ArithmExpression("-", new VarExpression("v"), new ConstExpression(1)));
        StmtInterface s5 = new ForkStmt(new CompStmt(s2,s4));


        StmtInterface s6 = new CompStmt(s1, new CompStmt(s2, new CompStmt(s3, new CompStmt(s4, s5))));


        StmtList.add(s6);
        ObservableList<String> list = FXCollections.observableArrayList();
        for(StmtInterface i : StmtList)
            list.add(""+i);

        listView.setItems(list);

        listView.getSelectionModel().selectIndices(0);
    }

    @FXML
    public void buttonRun() throws IOException {
        statement = StmtList.get(listView.getSelectionModel().getSelectedIndex());

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("runProgram.fxml"));
        stage.setTitle("Running Program");
        stage.setScene(new Scene(root, 800, 600));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

}