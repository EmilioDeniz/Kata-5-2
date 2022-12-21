package view;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JPanel;
import model.Histogram;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class HistogramDisplay extends ApplicationFrame{

    private Histogram<String> hist;
    
    public HistogramDisplay(String title,Histogram<String> histogram) {
        super(title);
        hist = histogram;
        createChart(createDataSet());
        setContentPane(createPanel());
        pack();
    }

    public void execute(){
        setVisible(true);
    }

    private JPanel createPanel() {
        ChartPanel panel = new ChartPanel(createChart(createDataSet()));
        panel.setPreferredSize(new Dimension(500,400));
        return panel;
    }

    private JFreeChart createChart(DefaultCategoryDataset data) {
        JFreeChart chart = ChartFactory.createBarChart("Histogram Display",
                                                                        "Dominios E-Mails", 
                                                                        "Nº emails", 
                                                                        data, 
                                                                        PlotOrientation.VERTICAL,
                                                                        false,
                                                                        false, 
                                                                        rootPaneCheckingEnabled);
        return chart;
    }

    private DefaultCategoryDataset createDataSet() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for(String key: hist.keySet()){
            dataset.addValue(hist.get(key),"",key);
        }
        
        return dataset;
    }
}
