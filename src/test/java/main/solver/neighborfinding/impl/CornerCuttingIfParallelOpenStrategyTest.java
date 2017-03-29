package main.solver.neighborfinding.impl;

import main.solver.datastructures.AStarNode;
import main.solver.datastructures.impl.HeuristicNodeImpl;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CornerCuttingIfParallelOpenStrategyTest extends NeighborFindingStrategyTest {

    private List<AStarNode> oneCornerAndOneParallelOpen = Arrays.asList(
            new HeuristicNodeImpl(1, 1, blocked, strategy),
            new HeuristicNodeImpl(1, 2, blocked, strategy),
            new HeuristicNodeImpl(1, 3, open, strategy),
            new HeuristicNodeImpl(2, 1, blocked, strategy),
            new HeuristicNodeImpl(2, 3, open, strategy),
            new HeuristicNodeImpl(3, 1, blocked, strategy),
            new HeuristicNodeImpl(3, 2, blocked, strategy),
            new HeuristicNodeImpl(3, 3, blocked, strategy)
    );
    private List<AStarNode> oneCornerAndTwoParallelOpen = Arrays.asList(
            new HeuristicNodeImpl(1, 1, blocked, strategy),
            new HeuristicNodeImpl(1, 2, open, strategy),
            new HeuristicNodeImpl(1, 3, open, strategy),
            new HeuristicNodeImpl(2, 1, blocked, strategy),
            new HeuristicNodeImpl(2, 3, open, strategy),
            new HeuristicNodeImpl(3, 1, blocked, strategy),
            new HeuristicNodeImpl(3, 2, blocked, strategy),
            new HeuristicNodeImpl(3, 3, blocked, strategy)
    );

    public CornerCuttingIfParallelOpenStrategyTest() {
        super(new CornerCuttingIfParallelOpenStrategy());
    }

    @Test
    public void whenAllNeighborsOpen_expectRootNodeNeighborsContainAllNeighbors() {
        rootNode.setNeighbors(allOpenNeighbors);
        List<AStarNode> validNeighbors = rootNode.getValidNeighbors();
        assertEquals(allOpenNeighbors, validNeighbors);
    }

    @Test
    public void whenAllNeighborsBlocked_expectNoValidNeighbors() {
        rootNode.setNeighbors(allBlockedNeighbors);
        List<AStarNode> validNeighbors = rootNode.getValidNeighbors();
        assertEquals(Collections.emptyList(), validNeighbors);
    }

    @Test
    public void whenCornerNeighborsBlockedAndParallelOpen_expectRootNodeNeighborsContainParallelNeighborsOnly() {
        rootNode.setNeighbors(cornersBlockedNeighbors);
        List<AStarNode> validNeighbors = rootNode.getValidNeighbors();
        assertEquals(parallelOpen, validNeighbors);
    }

    @Test
    public void whenCornerNeighborsOpenAndParallelBlocked_expectNoValidNeighbors() {
        rootNode.setNeighbors(cornersOpenNeighbors);
        List<AStarNode> validNeighbors = rootNode.getValidNeighbors();
        assertEquals(Collections.emptyList(), validNeighbors);
    }

    @Test
    public void whenOneParallelAndOneCornerIsOpen_expectOnlyParallelAsValidNeighbor() {
        rootNode.setNeighbors(oneCornerAndOneParallelOpen);
        List<AStarNode> validNeighbors = rootNode.getValidNeighbors();
        assertEquals(Collections.singletonList(new HeuristicNodeImpl(2, 3, open, strategy)), validNeighbors);
    }

    @Test
    public void whenOneCornerAndTwoParallelAreOpen_expectOneCornerAndTwoParallelAsValidNeighbors() {
        rootNode.setNeighbors(oneCornerAndTwoParallelOpen);
        List<AStarNode> validNeighbors = rootNode.getValidNeighbors();
        assertEquals(Arrays.asList(
                new HeuristicNodeImpl(1, 2, open, strategy),
                new HeuristicNodeImpl(1, 3, open, strategy),
                new HeuristicNodeImpl(2, 3, open, strategy)
        ), validNeighbors);
    }

}