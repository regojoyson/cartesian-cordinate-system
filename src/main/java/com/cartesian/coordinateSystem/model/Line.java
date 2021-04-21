package com.cartesian.coordinateSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This represent the Line using 2 Points
 *
 * @author RegoJoyson
 * @since 21-apr-2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Line {

    private Point start;
    private Point end;

}
