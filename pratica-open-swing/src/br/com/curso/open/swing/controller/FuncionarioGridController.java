/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.open.swing.controller;

import br.com.curso.open.swing.dao.FuncionarioDao;
import br.com.curso.open.swing.util.Util;
import br.com.curso.open.swing.view.FuncionarioView;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;

/**
 *
 * @author jonat
 */
public class FuncionarioGridController extends GridController implements GridDataLocator {

    public FuncionarioView funcionarioView;
    public FuncionarioDao funcionarioDao = new FuncionarioDao();

    public FuncionarioGridController() {

        funcionarioView = new FuncionarioView();

        /*Organizando o controle da grid lá da visão*/
        this.funcionarioView.getGridControlFuncionario().setController(FuncionarioGridController.this);
        this.funcionarioView.getGridControlFuncionario().setGridDataLocator(FuncionarioGridController.this);

        funcionarioView.setVisible(true);

        this.funcionarioView.getGridControlFuncionario().getTable().setDimensionFilterDialog(new Dimension(480, 500));
    }

    @Override
    public Response loadData(int action,
            int startIndex,
            Map filteredColumns,
            ArrayList currentSortedColumns,
            ArrayList currentSortedVersusColumns,
            Class valueObjectType,
            Map otherGridParams) {

        filteredColumns = Util.ajustaFiltro(filteredColumns);

        GridParams gridParams = new GridParams(
                action,
                startIndex,
                filteredColumns,
                currentSortedColumns,
                currentSortedVersusColumns,
                new HashMap()
        );
        return funcionarioDao.loadData(gridParams);
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        return this.funcionarioDao.insertRecords(newValueObjects);
    }

    @Override
    public void afterInsertGrid(GridControl grid) {
        grid.reloadData();
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        return this.funcionarioDao.updateRecords(persistentObjects);
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        return funcionarioDao.deleteRecords(persistentObjects);
    }

    public FuncionarioView getFuncionarioView() {
        return funcionarioView;
    }
}
