package com.cartesian.coordinateSystem.controller;


import com.cartesian.coordinateSystem.model.Point;
import com.cartesian.coordinateSystem.model.TwoLineModel;
import com.cartesian.coordinateSystem.service.CartesianService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This Controller defines the route for the Cartesian coordinates related API's
 *
 * @author RegoJoyson
 * @since 21-apr-2021
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CartesianController {

    private Logger logger = LoggerFactory.getLogger(CartesianController.class);

    private final CartesianService cartesianService;

    /**
     * Definition of a line by means of two points
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return returns true if its valid line
     */
    @PostMapping("/line-by-two-points/{x1}/{y1}/{x2}/{y2}")
    public ResponseEntity<Boolean> isLineUsingTwoPoints(@PathVariable double x1, @PathVariable double y1,
                                                        @PathVariable double x2, @PathVariable double y2) {
        logger.debug("Is Line using two points? x1: {}, y1={}, x2={}, y2={}", x1, y1, x2, y2);
        return ResponseEntity.ok(cartesianService.isLineUsingTwoPoints(new Point(x1, y1), new Point(x2, y2)));
    }

    /**
     * Definition of a line by means of gradient and y-intercept
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return returns the intercept coordinate point
     */
    @PostMapping("/y-intercept/{x1}/{y1}/{x2}/{y2}")
    public ResponseEntity<String> findGradientAndYIntercept(@PathVariable double x1, @PathVariable double y1,
                                                            @PathVariable double x2, @PathVariable double y2) {
        logger.debug("find Y intercept by two points x1: {}, y1={}, x2={}, y2={}", x1, y1, x2, y2);
        return ResponseEntity.ok(cartesianService.findGradientAndYIntercept(new Point(x1, y1), new Point(x2, y2)));
    }

    /**
     * Condition of parallelism of two lines
     *
     * @param twoLineModel
     * @return returns true if parallel
     */
    @PostMapping("/parallel")
    public ResponseEntity<Boolean> isParallel(@RequestBody TwoLineModel twoLineModel) {
        logger.debug("check the two lines are parallel or not");
        return ResponseEntity.ok(cartesianService.isParallel(twoLineModel.getLine1(), twoLineModel.getLine2()));
    }

    /**
     * Condition of perpendicularity of two lines
     *
     * @param twoLineModel
     * @return returns true if perpendicular
     */
    @PostMapping("/perpendicular")
    public ResponseEntity<Boolean> isPerpendicular(@RequestBody TwoLineModel twoLineModel) {
        logger.debug("check the two lines are perpendicular or not");
        return ResponseEntity.ok(cartesianService.isPerpendicular(twoLineModel.getLine1(), twoLineModel.getLine2()));
    }

    /**
     * Condition of incidence of two lines and definition of the incidence point
     *
     * @param twoLineModel
     * @return return the incident point
     */
    @PostMapping("/line-incident-point")
    public ResponseEntity<Point> lineIncidencePoint(@RequestBody TwoLineModel twoLineModel) {
        logger.debug("get the line incident point");
        return ResponseEntity.ok(cartesianService.lineIncidencePoint(twoLineModel.getLine1(), twoLineModel.getLine2()));
    }


}
