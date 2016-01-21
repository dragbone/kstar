/**
 * Created by dragb on 07.01.2016.
 */
data class StateEntry<TState>(val previousState: TState?, var g: Int, var h: Int) {
    val f: Int
        get() = g + h
}