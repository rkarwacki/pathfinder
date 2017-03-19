package main.solver.neighborfinding.impl;

import main.solver.AStarNode;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CornerCuttingStrategyTest extends NeighborFindingStrategyTest {

    public CornerCuttingStrategyTest() {
        super(new CornerCuttingStrategy());
    }

    @Test
    public void whenAllNeighborsOpen_expectRootNodeNeighborsContainAllNeighbors(){
        rootNode.setNeighbors(allOpenNeighbors);
        List<AStarNode> validNeighbors = rootNode.getValidNeighbors();
        assertEquals(allOpenNeighbors, validNeighbors);
    }

    @Test
    public void whenAllNeighborsBlocked_expectNoValidNeighbors(){
        rootNode.setNeighbors(allBlockedNeighbors);
        List<AStarNode> validNeighbors = rootNode.getValidNeighbors();
        assertEquals(Collections.emptyList(), validNeighbors);
    }

    @Test
    public void whenCornerNeighborsBlockedAndParallelOpen_expectRootNodeNeighborsContainParallelNeighborsOnly(){
        rootNode.setNeighbors(cornersBlockedNeighbors);
        List<AStarNode> validNeighbors = rootNode.getValidNeighbors();
        assertEquals(parallelOpen, validNeighbors);
    }

    @Test
    public void whenCornerNeighborsOpenAndParallelBlocked_expectRootNodeNeighborsContainCornerNeighborsOnly(){
        rootNode.setNeighbors(cornersOpenNeighbors);
        List<AStarNode> validNeighbors = rootNode.getValidNeighbors();
        assertEquals(cornersOpen, validNeighbors);
    }

}
