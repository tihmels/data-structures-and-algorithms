package section5.chapter19.representations

import section5.chapter19.core.*

/**
 * Adjacency matrix is generally suitable for dense graphs, when your graph has lots of edges.
 */
abstract class AdjacencyMatrix<V, E : Edge<V>> : Graph<V, E> {

    protected val vertices = arrayListOf<Vertex<V>>()
    protected val weights = arrayListOf<ArrayList<Double>>()

    override fun createVertex(data: V): Vertex<V> {
        val vertex = Vertex(vertices.count(), data)

        vertices.add(vertex)
        weights.forEach { it.add(0.0) }

        val row = ArrayList<Double>(vertices.count())
        repeat(vertices.count()) {
            row.add(0.0)
        }

        weights.add(row)
        return vertex
    }

    override fun getVertexEdges(source: Vertex<V>): Set<E> {
        val edges = arrayListOf<E>()
        (0 until weights.size).forEach { column ->
            val weight = weights[source.index][column]
            if (weight != 0.0) {
                edges.add(getEdge(source.index, column))
            }
        }
        return edges.toSet()
    }

    abstract fun getEdge(sourceIndex: Int, destinationIndex: Int): E

    override fun toString(): String {

        val verticesDescription = vertices.joinToString(separator = "\n") { "${it.index}: ${it.data}" }
        val grid = weights.map { row ->
            buildString {
                (0 until weights.size).forEach { columnIndex ->
                    val value = row[columnIndex]
                    if (value != 0.0) {
                        append("$value\t")
                    } else {
                        append("ø\t\t")
                    }
                }

            }
        }
        val edgesDescription = grid.joinToString("\n")
        return "$verticesDescription\n\n$edgesDescription"
    }

}

class UnweightedAdjacencyMatrix<V> : AdjacencyMatrix<V, Edge<V>>(), UnweightedGraph<V> {

    override fun addDirectedEdge(source: Vertex<V>, destination: Vertex<V>) {
        weights[source.index][destination.index] = 1.0
    }

    override fun getEdge(sourceIndex: Int, destinationIndex: Int): Edge<V> {
        return Edge(vertices[sourceIndex], vertices[destinationIndex])
    }

}

class WeightedAdjacencyMatrix<V> : AdjacencyMatrix<V, WeightedEdge<V>>(), WeightedGraph<V> {

    override fun addDirectedEdge(source: Vertex<V>, destination: Vertex<V>, weight: Double) {
        weights[source.index][destination.index] = weight
    }

    override fun getWeight(source: Vertex<V>, destination: Vertex<V>): Double {
        return weights[source.index][destination.index]
    }

    override fun getEdge(sourceIndex: Int, destinationIndex: Int): WeightedEdge<V> {
        return WeightedEdge(
            vertices[sourceIndex],
            vertices[destinationIndex],
            weights[sourceIndex][destinationIndex]
        )
    }

}