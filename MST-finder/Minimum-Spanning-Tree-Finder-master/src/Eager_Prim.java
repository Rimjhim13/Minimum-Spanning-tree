

import java.util.*;

public class Eager_Prim {
    private double weight=0;
    private Edge edgeTo[];
    private double distTo[];
    private boolean checked[];
    private IndexMinPQ<Double> pq;
    
    public Eager_Prim(EdgeWeightedGraph g){
        edgeTo=new Edge[g.V()];
        distTo=new double[g.V()];
        checked=new boolean[g.V()];
        pq=new IndexMinPQ<Double>(g.V());
        for(int i=0;i<g.V();i++)
            distTo[i]=Double.POSITIVE_INFINITY;
        distTo[0]=0;
        pq.insert(0,0.0);
        while(!pq.isEmpty()){
            visit(g,pq.delMin());
            
        }
    }
    private void visit(EdgeWeightedGraph g, int v){
        checked[v]=true;
        for(Edge e : g.adj(v))
        {
            int w=e.other(v);
            if(checked[w]) continue;
            if(e.weight() < distTo[w])
            {
                if(distTo[w]!=Double.POSITIVE_INFINITY)
                    weight-=distTo[w];
                weight+=e.weight();
                edgeTo[w]=e;
                distTo[w]=e.weight();
                if(pq.contains(w)) pq.change(w,distTo[w]);
                else               pq.insert(w,distTo[w]);
            }
        }
    }
    
public Iterable<Edge> edges()
{
LinkedList<Edge> mst = new LinkedList<Edge>();
for (int v = 1; v < edgeTo.length; v++)
{
    mst.push(edgeTo[v]);
}
return mst;
}
    public double weight(){
        return weight;
    }
    public void show(){
        for(int i=0;i<edgeTo.length;i++)
            System.out.println(edgeTo[i]);
    }
}


//=============================================================================