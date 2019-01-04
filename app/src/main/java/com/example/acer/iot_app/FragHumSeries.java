package com.example.acer.iot_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Scatter;
import com.anychart.core.scatter.series.Line;
import com.anychart.core.scatter.series.Marker;
import com.anychart.enums.HoverMode;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipDisplayMode;
import com.anychart.graphics.vector.GradientKey;
import com.anychart.graphics.vector.LinearGradientStroke;
import com.anychart.graphics.vector.SolidFill;
import com.anychart.graphics.vector.text.HAlign;

import java.util.ArrayList;
import java.util.List;

//import com.anychart.sample.R;


public class FragHumSeries extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.tab_fragment_5, container, false);

        AnyChartView anyChartView = rootview.findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(rootview.findViewById(R.id.progressBar));

        Scatter scatter = AnyChart.scatter();

        scatter.animation(true);

        scatter.xScale()
                .minimum(0d)
                .maximum(100d);
//        scatter.xScale().tick
        scatter.yScale()
                .minimum(0d)
                .maximum(100d);

        scatter.yAxis(0).title("Humidity");
        scatter.xAxis(0)
                .title("Time")
                .drawFirstLabel(false)
                .drawLastLabel(false);

        scatter.interactivity()
                .hoverMode(HoverMode.BY_SPOT)
                .spotRadius(30d);

        scatter.tooltip().displayMode(TooltipDisplayMode.UNION);

        Marker marker = scatter.marker(getMarkerData());
        marker.type(MarkerType.TRIANGLE_UP)
                .size(4d);
        marker.hovered()
                .size(7d)
                .fill(new SolidFill("gold", 1d))
                .stroke("anychart.color.darken(gold)");
        marker.tooltip()
                .hAlign(HAlign.START)
                .format("Waiting time: ${%Value} min.\\nDuration: ${%X} min.");

        Line scatterSeriesLine = scatter.line(getLineData());

        GradientKey gradientKey[] = new GradientKey[] {
                new GradientKey("#abcabc", 0d, 1d),
                new GradientKey("#cbacba", 40d, 1d)
        };
        LinearGradientStroke linearGradientStroke = new LinearGradientStroke(0d, null, gradientKey, null, null, true, 1d, 2d);
        scatterSeriesLine.stroke(linearGradientStroke, 3d, null, (String) null, (String) null);

        anyChartView.setChart(scatter);


        return rootview;
    }


    private List<DataEntry> getLineData() {
        List<DataEntry> data = new ArrayList<>();
        float x = 0;
        float y = 27;
        while(x<100)
            x+=1;
            y+=0.03;
            data.add(new ValueDataEntry(x,y));
        return data;
    }

    private List<DataEntry> getMarkerData() {
        List<DataEntry> data = new ArrayList<>();

        data.add(new ValueDataEntry(5, 80));
        data.add(new ValueDataEntry(15, 82));
        data.add(new ValueDataEntry(25, 83));
        data.add(new ValueDataEntry(28, 78));
        data.add(new ValueDataEntry(34, 77));
        data.add(new ValueDataEntry(35, 77));
        data.add(new ValueDataEntry(42, 79));
        data.add(new ValueDataEntry(45, 82));
        data.add(new ValueDataEntry(47, 83));
        data.add(new ValueDataEntry(55, 84));
        data.add(new ValueDataEntry(65, 87));
        data.add(new ValueDataEntry(75, 88));
        data.add(new ValueDataEntry(85, 85));
        data.add(new ValueDataEntry(89, 83));
        data.add(new ValueDataEntry(91, 79));
        data.add(new ValueDataEntry(95, 79));

        return data;
    }
}
