/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.meteoinfo.desktop.forms;

import java.awt.Cursor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import org.meteoinfo.layer.LayerTypes;
import org.meteoinfo.layer.VectorLayer;
import org.meteoinfo.desktop.config.GenericFileFilter;
import org.meteoinfo.data.meteodata.GridDataSetting;
import org.meteoinfo.data.meteodata.Variable;
import org.meteoinfo.data.meteodata.grads.GrADSDataInfo;
import org.meteoinfo.geoprocess.GeoComputation;
import org.meteoinfo.global.Extent;
import org.meteoinfo.global.MIMath;
import org.meteoinfo.global.PointD;
import org.meteoinfo.shape.PointShape;
import org.meteoinfo.shape.PolygonShape;
import org.meteoinfo.shape.PolylineShape;
import org.meteoinfo.shape.Shape;

/**
 *
 * @author yaqiang
 */
public class FrmOutputMapData extends javax.swing.JDialog {

    private List<VectorLayer> _mapLayers = new ArrayList<>();
    private VectorLayer _currentLayer;
    private FrmMain _parent;

    /**
     * Creates new form FrmOutputMapData
     * @param parent
     * @param modal
     */
    public FrmOutputMapData(JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        _parent = (FrmMain) parent;
        initialize();
    }

    private void initialize() {
        this.jProgressBar1.setVisible(false);

        //Get map layer list
        int i;
        for (i = 0; i < _parent.getMapDocument().getActiveMapFrame().getMapView().getLayerNum(); i++) {
            if (_parent.getMapDocument().getActiveMapFrame().getMapView().getLayers().get(i).getLayerType() == LayerTypes.VectorLayer) {
                VectorLayer aLayer = (VectorLayer) _parent.getMapDocument().getActiveMapFrame().getMapView().getLayers().get(i);
                _mapLayers.add(aLayer);
            }
        }

        this.jComboBox_MapLayer.removeAllItems();
        if (_mapLayers.size() > 0) {
            for (i = 0; i < _mapLayers.size(); i++) {
                this.jComboBox_MapLayer.addItem(_mapLayers.get(i).getLayerName());
            }
            this.jComboBox_MapLayer.setSelectedIndex(0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox_MapLayer = new javax.swing.JComboBox();
        jComboBox_OutputFormat = new javax.swing.JComboBox();
        jButton_Output = new javax.swing.JButton();
        jButton_Close = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Output Map Data");

        jLabel1.setText("Map Layer:");

        jLabel2.setText("Output Format:");

        jComboBox_MapLayer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_MapLayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_MapLayerActionPerformed(evt);
            }
        });

        jComboBox_OutputFormat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton_Output.setText("Output");
        jButton_Output.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_OutputActionPerformed(evt);
            }
        });

        jButton_Close.setText("Close");
        jButton_Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_MapLayer, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_OutputFormat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jButton_Output)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Close)
                .addGap(49, 49, 49))
            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox_MapLayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox_OutputFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Output)
                    .addComponent(jButton_Close))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_MapLayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_MapLayerActionPerformed
        // TODO add your handling code here:
        if (this.jComboBox_MapLayer.getItemCount() > 0) {
            _currentLayer = _mapLayers.get(this.jComboBox_MapLayer.getSelectedIndex());

            int i;
            String str = this.jComboBox_OutputFormat.getSelectedItem().toString();
            this.jComboBox_OutputFormat.removeAllItems();
            this.jComboBox_OutputFormat.addItem("ASCII wmp File");
            this.jComboBox_OutputFormat.addItem("Shape File");
            this.jComboBox_OutputFormat.addItem("KML File");
            switch (_currentLayer.getShapeType()) {
                case Polygon:
                case PolygonM:
                case PolygonZ:
                    //this.jComboBox_OutputFormat.addItem("GrADS Map File");
                    this.jComboBox_OutputFormat.addItem("GrADS Maskout File");
                    //this.jComboBox_OutputFormat.addItem("Surfer BLN File");
                    break;
//                case Polyline:
//                    this.jComboBox_OutputFormat.addItem("GrADS Map File");
//                    this.jComboBox_OutputFormat.addItem("Surfer BLN File");
//                    break;
            }

            List<String> items = new ArrayList<>();
            for (i = 0; i < this.jComboBox_OutputFormat.getItemCount(); i++) {
                items.add(this.jComboBox_OutputFormat.getItemAt(i).toString());
            }

            int idx = items.indexOf(str);
            if (idx >= 0) {
                this.jComboBox_OutputFormat.setSelectedIndex(idx);
            } else {
                this.jComboBox_OutputFormat.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_jComboBox_MapLayerActionPerformed

    private void jButton_CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton_CloseActionPerformed

    private void jButton_OutputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_OutputActionPerformed
        // TODO add your handling code here:
        String format = this.jComboBox_OutputFormat.getSelectedItem().toString();
        if (format.equals("ASCII wmp File")) {
            try {
                saveWMPFile();
            } catch (IOException ex) {
                Logger.getLogger(FrmOutputMapData.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (format.equals("GrADS Map File")) {
            saveGrADSMapFile();
        } else if (format.equals("GrADS Maskout File")) {
            try {
                saveGrADSMaskoutFile();
            } catch (IOException ex) {
                Logger.getLogger(FrmOutputMapData.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (format.equals("Shape File")) {
            saveShapeFile();
        } else if (format.equals("Surfer BLN File")) {
            saveBLNMapFile();
        } else if (format.equals("KML File")) {
            saveKMLFile();
        }
    }//GEN-LAST:event_jButton_OutputActionPerformed

    private void saveWMPFile() throws IOException {
        JFileChooser aDlg = new JFileChooser();
        String[] fileExts = new String[]{"wmp"};
        GenericFileFilter mapFileFilter = new GenericFileFilter(fileExts, "wmp File (*.wmp)");
        aDlg.setFileFilter(mapFileFilter);
        File dir = new File(System.getProperty("user.dir"));
        if (dir.isDirectory()) {
            aDlg.setCurrentDirectory(dir);
        }
        aDlg.setAcceptAllFileFilterUsed(false);
        if (aDlg.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            //ProgressBar
            this.jProgressBar1.setVisible(true);
            this.jProgressBar1.setValue(0);
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

            File file = aDlg.getSelectedFile();
            System.setProperty("user.dir", file.getParent());
            String extent = ((GenericFileFilter) aDlg.getFileFilter()).getFileExtent();
            String fileName = file.getAbsolutePath();
            if (!fileName.substring(fileName.length() - extent.length()).equals(extent)) {
                fileName = fileName + "." + extent;
                file = new File(fileName);
            }

            BufferedWriter sw = new BufferedWriter(new FileWriter(file));
            List<Integer> selIndexes = _currentLayer.getSelectedShapeIndexes();
            boolean hasSelShape = _currentLayer.hasSelectedShapes();
            int shpNum = _currentLayer.getShapeNum();
            if (hasSelShape) {
                shpNum = selIndexes.size();
            }
            int i;
            switch (_currentLayer.getShapeType()) {
                case Point:
                    sw.write("Point");
                    sw.newLine();
                    sw.write(String.valueOf(shpNum));
                    sw.newLine();
                    PointShape aPS;
                    if (hasSelShape) {
                        for (i = 0; i < _currentLayer.getShapeNum(); i++) {
                            aPS = (PointShape) _currentLayer.getShapes().get(i);
                            if (aPS.isSelected()) {
                                sw.write(String.valueOf(aPS.getPoint().X) + "," + String.valueOf(aPS.getPoint().Y));
                                sw.newLine();
                            }
                            this.jProgressBar1.setValue((i + 1) * 100 / _currentLayer.getShapeNum());
                        }
                    } else {
                        for (i = 0; i < _currentLayer.getShapeNum(); i++) {
                            aPS = (PointShape) _currentLayer.getShapes().get(i);
                            sw.write(String.valueOf(aPS.getPoint().X) + "," + String.valueOf(aPS.getPoint().Y));
                            sw.newLine();
                            this.jProgressBar1.setValue((i + 1) * 100 / _currentLayer.getShapeNum());
                        }
                    }
                    break;
                case Polyline:
                case PolylineZ:
                    sw.write("Polyline");
                    sw.newLine();
                    int shapeNum = 0;
                    PolylineShape aPLS;
                    for (i = 0; i < _currentLayer.getShapeNum(); i++) {
                        aPLS = (PolylineShape) _currentLayer.getShapes().get(i);
                        if (hasSelShape) {
                            if (!aPLS.isSelected()) {
                                continue;
                            }
                        }
                        shapeNum += aPLS.getPartNum();
                    }
                    sw.write(String.valueOf(shpNum));
                    sw.newLine();

                    for (i = 0; i < _currentLayer.getShapeNum(); i++) {
                        aPLS = (PolylineShape) _currentLayer.getShapes().get(i);
                        if (hasSelShape) {
                            if (!aPLS.isSelected()) {
                                continue;
                            }
                        }
                        PointD[] Pointps;
                        for (int p = 0; p < aPLS.getPartNum(); p++) {
                            if (p == aPLS.getPartNum() - 1) {
                                Pointps = new PointD[aPLS.getPointNum() - aPLS.parts[p]];
                                for (int pp = aPLS.parts[p]; pp < aPLS.getPointNum(); pp++) {
                                    Pointps[pp - aPLS.parts[p]] = (PointD) aPLS.getPoints().get(pp);
                                }
                            } else {
                                Pointps = new PointD[aPLS.parts[p + 1] - aPLS.parts[p]];
                                for (int pp = aPLS.parts[p]; pp < aPLS.parts[p + 1]; pp++) {
                                    Pointps[pp - aPLS.parts[p]] = (PointD) aPLS.getPoints().get(pp);
                                }
                            }
                            sw.write(String.valueOf(Pointps.length));
                            sw.newLine();
                            for (PointD aPoint : Pointps) {
                                sw.write(String.valueOf(aPoint.X) + "," + String.valueOf(aPoint.Y));
                                sw.newLine();
                            }
                            shapeNum += 1;
                        }

                        this.jProgressBar1.setValue((i + 1) * 100 / _currentLayer.getShapeNum());
                    }
                    break;
                case Polygon:
                    sw.write("Polygon");
                    sw.newLine();
                    shapeNum = 0;
                    PolygonShape aPGS;
                    for (i = 0; i < _currentLayer.getShapeNum(); i++) {
                        aPGS = (PolygonShape) _currentLayer.getShapes().get(i);
                        if (hasSelShape) {
                            if (!aPGS.isSelected()) {
                                continue;
                            }
                        }
                        shapeNum += aPGS.getPartNum();
                    }
                    sw.write(String.valueOf(shapeNum));
                    sw.newLine();

                    for (i = 0; i < _currentLayer.getShapeNum(); i++) {
                        aPGS = (PolygonShape) _currentLayer.getShapes().get(i);
                        if (hasSelShape) {
                            if (!aPGS.isSelected()) {
                                continue;
                            }
                        }

                        PointD[] Pointps;
                        for (int p = 0; p < aPGS.getPartNum(); p++) {
                            if (p == aPGS.getPartNum() - 1) {
                                Pointps = new PointD[aPGS.getPointNum() - aPGS.parts[p]];
                                for (int pp = aPGS.parts[p]; pp < aPGS.getPointNum(); pp++) {
                                    Pointps[pp - aPGS.parts[p]] = (PointD) aPGS.getPoints().get(pp);
                                }
                            } else {
                                Pointps = new PointD[aPGS.parts[p + 1] - aPGS.parts[p]];
                                for (int pp = aPGS.parts[p]; pp < aPGS.parts[p + 1]; pp++) {
                                    Pointps[pp - aPGS.parts[p]] = (PointD) aPGS.getPoints().get(pp);
                                }
                            }
                            sw.write(String.valueOf(Pointps.length));
                            sw.newLine();
                            for (PointD aPoint : Pointps) {
                                sw.write(String.valueOf(aPoint.X) + "," + String.valueOf(aPoint.Y));
                                sw.newLine();
                            }
                            shapeNum += 1;
                        }

                        this.jProgressBar1.setValue((i + 1) * 100 / _currentLayer.getShapeNum());
                    }
                    break;
            }

            sw.close();

            //Progressbar
            this.jProgressBar1.setValue(0);
            this.jProgressBar1.setVisible(false);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    private void saveGrADSMapFile() {
    }

    private void saveGrADSMaskoutFile() throws IOException {
        JFileChooser aDlg = new JFileChooser();
        String[] fileExts = new String[]{"ctl"};
        GenericFileFilter mapFileFilter = new GenericFileFilter(fileExts, "GrADS File (*.ctl)");
        aDlg.setFileFilter(mapFileFilter);
        File dir = new File(System.getProperty("user.dir"));
        if (dir.isDirectory()) {
            aDlg.setCurrentDirectory(dir);
        }
        aDlg.setAcceptAllFileFilterUsed(false);
        if (aDlg.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

            File file = aDlg.getSelectedFile();
            System.setProperty("user.dir", file.getParent());
            String extent = ((GenericFileFilter) aDlg.getFileFilter()).getFileExtent();
            String fileName = file.getAbsolutePath();
            if (!fileName.substring(fileName.length() - extent.length()).equals(extent)) {
                fileName = fileName + "." + extent;
            }

            int i;
            boolean hasSelShape = _currentLayer.hasSelectedShapes();

            //Get grid set
            PolygonShape aPGS;
            Extent aExtent = new Extent();
            int n = 0;
            for (i = 0; i < _currentLayer.getShapeNum(); i++) {
                aPGS = (PolygonShape) _currentLayer.getShapes().get(i);
                if (hasSelShape) {
                    if (!aPGS.isSelected()) {
                        continue;
                    }
                }
                if (n == 0) {
                    aExtent = aPGS.getExtent();
                } else {
                    aExtent = MIMath.getLagerExtent(aExtent, aPGS.getExtent());
                }
                n += 1;
            }

            GridDataSetting aGDP = new GridDataSetting();
            aGDP.dataExtent.minX = Math.floor(aExtent.minX);
            aGDP.dataExtent.maxX = Math.ceil(aExtent.maxX);
            aGDP.dataExtent.minY = Math.floor(aExtent.minY);
            aGDP.dataExtent.maxY = Math.ceil(aExtent.maxY);
            aGDP.xNum = 20;
            aGDP.yNum = 20;

            FrmGridSet aFrmGS = new FrmGridSet((JFrame) SwingUtilities.getWindowAncestor(this), true);
            aFrmGS.setParameters(aGDP);
            aFrmGS.setLocationRelativeTo(this);
            aFrmGS.setVisible(true);
            if (aFrmGS.isOK()) {
                aGDP = aFrmGS.getParameters();

                //Show progressbar
                this.jProgressBar1.setVisible(true);
                this.jProgressBar1.setValue(0);
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                //Get grid data
                double[][] gridData = new double[aGDP.yNum][aGDP.xNum];
                int j, p;
                PointD aPoint = new PointD();
                double xSize, ySize;
                xSize = (aGDP.dataExtent.maxX - aGDP.dataExtent.minX) / (aGDP.xNum - 1);
                ySize = (aGDP.dataExtent.maxY - aGDP.dataExtent.minY) / (aGDP.yNum - 1);
                boolean isIn;
                for (i = 0; i < aGDP.yNum; i++) {
                    aPoint.Y = aGDP.dataExtent.minY + i * ySize;
                    for (j = 0; j < aGDP.xNum; j++) {
                        aPoint.X = aGDP.dataExtent.minX + j * xSize;
                        isIn = false;
                        for (p = 0; p < _currentLayer.getShapeNum(); p++) {
                            aPGS = (PolygonShape) _currentLayer.getShapes().get(p);
                            if (hasSelShape) {
                                if (!aPGS.isSelected()) {
                                    continue;
                                }
                            }
                            if (GeoComputation.pointInPolygon(aPGS, aPoint)) {
                                isIn = true;
                                break;
                            }
                        }
                        if (isIn) {
                            gridData[i][j] = 1;
                        } else {
                            gridData[i][j] = -1;
                        }
                    }
                    this.jProgressBar1.setValue((i + 1) * 100 / aGDP.yNum);
                }

                //Get GrADS data info
                String dFile = fileName.replace(".ctl", ".dat");
                GrADSDataInfo aDataInfo = new GrADSDataInfo();
                aDataInfo.setFileName(fileName);
                aDataInfo.TITLE = "Mask data";
                aDataInfo.DSET = dFile;
                aDataInfo.DTYPE = "GRIDDED";
                aDataInfo.XDEF.Type = "LINEAR";
                aDataInfo.XDEF.XNum = aGDP.xNum;
                aDataInfo.XDEF.XMin = (float) aGDP.dataExtent.minX;
                aDataInfo.XDEF.XDelt = (float) (xSize);
                aDataInfo.YDEF.Type = "LINEAR";
                aDataInfo.YDEF.YNum = aGDP.yNum;
                aDataInfo.YDEF.YMin = (float) aGDP.dataExtent.minY;
                aDataInfo.YDEF.YDelt = (float) (ySize);
                aDataInfo.ZDEF.Type = "LINEAR";
                aDataInfo.ZDEF.ZNum = 1;
                aDataInfo.ZDEF.SLevel = 1;
                aDataInfo.ZDEF.ZDelt = 1;
                aDataInfo.TDEF.Type = "LINEAR";
                //aDataInfo.TDEF.TNum = 1;
                aDataInfo.TDEF.STime = LocalDateTime.now();
                aDataInfo.TDEF.TDelt = "1mo";
                Variable aVar = new Variable();
                aVar.setName("mask");
                //aVar.LevelNum = 0;
                aVar.setUnits("99");
                aVar.setDescription("background mask data");
                aDataInfo.VARDEF.addVar(aVar);

                //Save files
                aDataInfo.writeGrADSCTLFile();
                aDataInfo.createDataFile(dFile);
                aDataInfo.writeGridData(gridData);
                aDataInfo.closeDataFile();
                this.jProgressBar1.setValue(0);
                this.jProgressBar1.setVisible(false);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }

    private void saveShapeFile() {
        JFileChooser aDlg = new JFileChooser();
        String[] fileExts = new String[]{"shp"};
        GenericFileFilter mapFileFilter = new GenericFileFilter(fileExts, "Shape File (*.shp)");
        aDlg.setFileFilter(mapFileFilter);
        File dir = new File(System.getProperty("user.dir"));
        if (dir.isDirectory()) {
            aDlg.setCurrentDirectory(dir);
        }
        aDlg.setAcceptAllFileFilterUsed(false);
        if (aDlg.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = aDlg.getSelectedFile();
            System.setProperty("user.dir", file.getParent());
            String extent = ((GenericFileFilter) aDlg.getFileFilter()).getFileExtent();
            String fileName = file.getAbsolutePath();
            if (!fileName.substring(fileName.length() - extent.length()).equals(extent)) {
                fileName = fileName + "." + extent;
            }

            this.saveShapeFile(fileName);
        }
    }

    private void saveShapeFile_bak(final String fileName) {
        SwingWorker worker = new SwingWorker<String, String>() {
            @Override
            protected String doInBackground() throws Exception {
                FrmOutputMapData.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                FrmOutputMapData.this.jProgressBar1.setVisible(true);
                FrmOutputMapData.this.jProgressBar1.setValue(0);

                //Create a VectorLayer with selected shapes
                int i, j;
                VectorLayer aLayer = new VectorLayer(_currentLayer.getShapeType());
                for (i = 0; i < _currentLayer.getFieldNumber(); i++) {
                    aLayer.editAddField(_currentLayer.getField(i).getColumnName(), _currentLayer.getField(i).getDataType());
                }
                boolean hasSelShape = _currentLayer.hasSelectedShapes();

                for (i = 0; i < _currentLayer.getShapeNum(); i++) {
                    Shape aPS = _currentLayer.getShapes().get(i);
                    if (hasSelShape) {
                        if (!aPS.isSelected()) {
                            continue;
                        }
                    }
                    int sNum = aLayer.getShapeNum();
                    try {
                        if (aLayer.editInsertShape(aPS, sNum)) {
                            for (j = 0; j < aLayer.getFieldNumber(); j++) {
                                aLayer.editCellValue(j, sNum, _currentLayer.getCellValue(j, i));
                            }
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(FrmOutputMapData.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    FrmOutputMapData.this.jProgressBar1.setValue((int) ((double) i / _currentLayer.getShapeNum() * 100));
                }
                aLayer.setProjInfo(_currentLayer.getProjInfo());
                aLayer.saveFile(fileName);

                return "";
            }

            @Override
            protected void done() {
                FrmOutputMapData.this.setCursor(Cursor.getDefaultCursor());
                FrmOutputMapData.this.jProgressBar1.setVisible(false);
            }
        };

        worker.execute();
    }

    private void saveShapeFile(final String fileName) {
        FrmOutputMapData.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        FrmOutputMapData.this.jProgressBar1.setVisible(true);
        FrmOutputMapData.this.jProgressBar1.setValue(0);

        //Create a VectorLayer with selected shapes
        int i, j;
        VectorLayer aLayer = new VectorLayer(_currentLayer.getShapeType());
        for (i = 0; i < _currentLayer.getFieldNumber(); i++) {
            aLayer.editAddField(_currentLayer.getField(i).getColumnName(), _currentLayer.getField(i).getDataType());
        }
        boolean hasSelShape = _currentLayer.hasSelectedShapes();

        for (i = 0; i < _currentLayer.getShapeNum(); i++) {
            Shape aPS = _currentLayer.getShapes().get(i);
            if (hasSelShape) {
                if (!aPS.isSelected()) {
                    continue;
                }
            }
            int sNum = aLayer.getShapeNum();
            try {
                if (aLayer.editInsertShape(aPS, sNum)) {
                    for (j = 0; j < aLayer.getFieldNumber(); j++) {
                        aLayer.editCellValue(j, sNum, _currentLayer.getCellValue(j, i));
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(FrmOutputMapData.class.getName()).log(Level.SEVERE, null, ex);
            }
            FrmOutputMapData.this.jProgressBar1.setValue((int) ((double) i / _currentLayer.getShapeNum() * 100));
        }
        aLayer.setProjInfo(_currentLayer.getProjInfo());
        aLayer.saveFile(fileName);
        
        FrmOutputMapData.this.setCursor(Cursor.getDefaultCursor());
        FrmOutputMapData.this.jProgressBar1.setVisible(false);
    }

    private void saveBLNMapFile() {
    }

    private void saveKMLFile() {
        JFileChooser aDlg = new JFileChooser();
        String[] fileExts = new String[]{"kml"};
        GenericFileFilter mapFileFilter = new GenericFileFilter(fileExts, "KML File (*.kml)");
        aDlg.setFileFilter(mapFileFilter);
        File dir = new File(System.getProperty("user.dir"));
        if (dir.isDirectory()) {
            aDlg.setCurrentDirectory(dir);
        }
        aDlg.setAcceptAllFileFilterUsed(false);
        if (aDlg.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

            File file = aDlg.getSelectedFile();
            System.setProperty("user.dir", file.getParent());
            String extent = ((GenericFileFilter) aDlg.getFileFilter()).getFileExtent();
            String fileName = file.getAbsolutePath();
            if (!fileName.substring(fileName.length() - extent.length()).equals(extent)) {
                fileName = fileName + "." + extent;
            }
            _currentLayer.saveAsKMLFile(fileName);

            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmOutputMapData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmOutputMapData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmOutputMapData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmOutputMapData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FrmOutputMapData dialog = new FrmOutputMapData(new FrmMain(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Close;
    private javax.swing.JButton jButton_Output;
    private javax.swing.JComboBox jComboBox_MapLayer;
    private javax.swing.JComboBox jComboBox_OutputFormat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
