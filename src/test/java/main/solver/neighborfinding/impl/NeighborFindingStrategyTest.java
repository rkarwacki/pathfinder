package main.solver.neighborfinding.impl;

import main.solver.AStarNode;
import main.solver.State;
import main.solver.impl.AStarNodeImpl;
import main.solver.neighborfinding.NeighborFindingStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NeighborFindingStrategyTest {

    protected NeighborFindingStrategy<AStarNode> strategy;
    protected AStarNode rootNode;
    protected List<AStarNode> cornersOpen;
    protected List<AStarNode> cornersBlocked;
    protected List<AStarNode> parallelOpen;
    protected List<AStarNode> parallelBlocked;
    protected List<AStarNode> allOpenNeighbors;
    protected List<AStarNode> allBlockedNeighbors;
    protected List<AStarNode> cornersOpenNeighbors;
    protected List<AStarNode> cornersBlockedNeighbors;

    protected State blocked = State.BLOCKED;
    protected State open = State.OPEN;

    public NeighborFindingStrategyTest(NeighborFindingStrategy<AStarNode> strategy) {
        this.strategy = strategy;
        createNeighborsLists(strategy);
    }

    private void createNeighborsLists(NeighborFindingStrategy<AStarNode> strategy) {
        rootNode = new AStarNodeImpl(2, 2, State.OPEN, strategy);

        cornersOpen = createCorners(open);
        cornersBlocked = createCorners(blocked);
        parallelOpen = createParallel(open);
        parallelBlocked = createParallel(blocked);

        allOpenNeighbors = Stream.concat(cornersOpen.stream(), parallelOpen.stream()).collect(Collectors.toList());
        allBlockedNeighbors = Stream.concat(cornersBlocked.stream(), parallelBlocked.stream()).collect(Collectors.toList());
        cornersOpenNeighbors = Stream.concat(cornersOpen.stream(), parallelBlocked.stream()).collect(Collectors.toList());
        cornersBlockedNeighbors = Stream.concat(cornersBlocked.stream(), parallelOpen.stream()).collect(Collectors.toList());
    }

    private List<AStarNode> createParallel(State state) {
        return Arrays.asList(
                new AStarNodeImpl(1, 2, state, strategy),
                new AStarNodeImpl(2, 1, state, strategy),
                new AStarNodeImpl(2, 3, state, strategy),
                new AStarNodeImpl(3, 2, state, strategy)
        );
    }

    private List<AStarNode> createCorners(State state) {
        return Arrays.asList(
                new AStarNodeImpl(1, 1, state, strategy),
                new AStarNodeImpl(1, 3, state, strategy),
                new AStarNodeImpl(3, 1, state, strategy),
                new AStarNodeImpl(3, 3, state, strategy)
        );
    }
}
