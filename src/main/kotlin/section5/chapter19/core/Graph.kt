package section5.chapter19.core

enum class EdgeType {
    UNDIRECTED, DIRECTED
}

interface Graph<V, E : Edge<V>> {

    fun createVertex(data: V): Vertex<V>
    fun getVertexEdges(source: Vertex<V>): Set<E>

}

interface UnweightedGraph<V> : Graph<V, Edge<V>> {

    fun addDirectedEdge(source: Vertex<V>, destination: Vertex<V>)

    fun addUndirectedEdge(source: Vertex<V>, destination: Vertex<V>) {
        addDirectedEdge(source, destination)
        addDirectedEdge(destination, source)
    }

    fun addEdge(source: Vertex<V>, destination: Vertex<V>, edgeType: EdgeType) = when (edgeType) {
        EdgeType.UNDIRECTED -> addUndirectedEdge(source, destination)
        EdgeType.DIRECTED -> addDirectedEdge(source, destination)
    }

}

interface WeightedGraph<V> : Graph<V, WeightedEdge<V>> {

    fun addDirectedEdge(source: Vertex<V>, destination: Vertex<V>, weight: Double)

    fun addUndirectedEdge(source: Vertex<V>, destination: Vertex<V>, weight: Double) {
        addDirectedEdge(source, destination, weight)
        addDirectedEdge(destination, source, weight)
    }

    fun addEdge(source: Vertex<V>, destination: Vertex<V>, weight: Double, edgeType: EdgeType) = when (edgeType) {
        EdgeType.UNDIRECTED -> addUndirectedEdge(source, destination, weight)
        EdgeType.DIRECTED -> addDirectedEdge(source, destination, weight)
    }

    fun getWeight(source: Vertex<V>, destination: Vertex<V>): Double?

}
