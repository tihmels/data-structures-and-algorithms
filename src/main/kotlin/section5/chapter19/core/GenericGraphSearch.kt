package section5.chapter19.core

import java.util.*

typealias Successor<V> = (V) -> List<V>

object GenericGraphSearch {

    fun <V> pathCount(
        start: Vertex<V>,
        destination: Vertex<V>,
        successors: Successor<Vertex<V>>
    ): Int {
        var numberOfPaths = 0

        val visited = mutableSetOf(start)
        val frontier = LinkedList<Vertex<V>>()
        frontier.offer(start)

        while (frontier.isNotEmpty()) {

            val vertex = frontier.poll()

            if (vertex == destination) {
                numberOfPaths++
            }

            for (successor in successors(vertex)) {

                if (successor == destination) numberOfPaths++
                if (visited.contains(successor)) continue

                visited.add(successor)
                frontier.offer(successor)

            }
        }

        return numberOfPaths

    }

}