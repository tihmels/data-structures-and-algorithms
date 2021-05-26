package section5.chapter19.core

open class Edge<T>(val source: Vertex<T>, val destination: Vertex<T>) {
    fun reversed() = Edge(destination, source)
}

class WeightedEdge<T>(source: Vertex<T>, destination: Vertex<T>, val weight: Double) : Edge<T>(source, destination)

infix fun <T> Vertex<T>.to(to: Vertex<T>) = Edge(this, to)
infix fun <T> Edge<T>.withWeight(weight: Double) = WeightedEdge(this.source, this.destination, weight)