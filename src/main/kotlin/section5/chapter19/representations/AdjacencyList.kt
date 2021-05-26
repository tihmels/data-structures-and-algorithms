package section5.chapter19.representations

import section5.chapter19.core.*

/**
 * Adjacency list is generally good for sparse graphs, when your graph has the least amount of edges.
 */
abstract class AdjacencyList<V, E : Edge<V>> : Graph<V, E> {

    protected val adjacencies: HashMap<Vertex<V>, HashSet<E>> = HashMap()

    override fun createVertex(data: V): Vertex<V> {
        val vertex = Vertex(adjacencies.count(), data)
        adjacencies[vertex] = HashSet()
        return vertex
    }

    override fun getVertexEdges(source: Vertex<V>): Set<E> {
        return adjacencies[source] ?: emptySet()
    }

    override fun toString(): String {
        return buildString {
            adjacencies.forEach { (vertex, edges) ->
                val edgeString = edges.joinToString { it.destination.data.toString() }
                append("${vertex.data} --> [ $edgeString ]\n")
            }
        }
    }

}

class UnweightedAdjacencyList<V> : AdjacencyList<V, Edge<V>>(), UnweightedGraph<V> {

    override fun addDirectedEdge(source: Vertex<V>, destination: Vertex<V>) {
        val edge = source to destination
        adjacencies[source]?.add(edge)
    }

}

class WeightedAdjacencyList<V> : AdjacencyList<V, WeightedEdge<V>>(), WeightedGraph<V> {

    override fun addDirectedEdge(source: Vertex<V>, destination: Vertex<V>, weight: Double) {
        val edge = source to destination withWeight weight
        adjacencies[source]?.add(edge)
    }

    override fun getWeight(source: Vertex<V>, destination: Vertex<V>): Double? =
        adjacencies[source]?.firstOrNull { it.destination == destination }?.weight


}
