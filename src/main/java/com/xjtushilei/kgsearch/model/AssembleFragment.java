package com.xjtushilei.kgsearch.model;

/**
 * @author yuanhao
 * @date 2017/12/20 16:58
 */
public class AssembleFragment {

    private int FragmentID;
    private String FragmentContent;
    private String Text;
    private String FragmentScratchTime;
    private int TermID;
    private String TermName;
    private String FacetName;
    private int FacetLayer;
    private String ClassName;
    private String SourceName;

    @Override
    public String toString() {
        return "AssembleFragment2{" +
                "FragmentID=" + FragmentID +
                ", FragmentContent='" + FragmentContent + '\'' +
                ", Text='" + Text + '\'' +
                ", FragmentScratchTime='" + FragmentScratchTime + '\'' +
                ", TermID=" + TermID +
                ", TermName='" + TermName + '\'' +
                ", FacetName='" + FacetName + '\'' +
                ", FacetLayer=" + FacetLayer +
                ", ClassName='" + ClassName + '\'' +
                ", SourceName='" + SourceName + '\'' +
                '}';
    }

    public int getFragmentID() {
        return FragmentID;
    }

    public void setFragmentID(int fragmentID) {
        FragmentID = fragmentID;
    }

    public String getFragmentContent() {
        return FragmentContent;
    }

    public void setFragmentContent(String fragmentContent) {
        FragmentContent = fragmentContent;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getFragmentScratchTime() {
        return FragmentScratchTime;
    }

    public void setFragmentScratchTime(String fragmentScratchTime) {
        FragmentScratchTime = fragmentScratchTime;
    }

    public int getTermID() {
        return TermID;
    }

    public void setTermID(int termID) {
        TermID = termID;
    }

    public String getTermName() {
        return TermName;
    }

    public void setTermName(String termName) {
        TermName = termName;
    }

    public String getFacetName() {
        return FacetName;
    }

    public void setFacetName(String facetName) {
        FacetName = facetName;
    }

    public int getFacetLayer() {
        return FacetLayer;
    }

    public void setFacetLayer(int facetLayer) {
        FacetLayer = facetLayer;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getSourceName() {
        return SourceName;
    }

    public void setSourceName(String sourceName) {
        SourceName = sourceName;
    }

    public AssembleFragment() {

    }

    public AssembleFragment(int fragmentID, String fragmentContent, String text, String fragmentScratchTime, int termID, String termName, String facetName, int facetLayer, String className, String sourceName) {
        FragmentID = fragmentID;
        FragmentContent = fragmentContent;
        Text = text;
        FragmentScratchTime = fragmentScratchTime;
        TermID = termID;
        TermName = termName;
        FacetName = facetName;
        FacetLayer = facetLayer;
        ClassName = className;
        SourceName = sourceName;
    }
}