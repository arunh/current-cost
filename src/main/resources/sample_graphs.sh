#!/bin/bash

rrdtool graph /tmp/power-10min.png \
--start end-10m --width 700 --end now --slope-mode \
--no-legend --vertical-label Watts --lower-limit 0 \
--alt-autoscale-max \
DEF:Power=/tmp/current_cost.rrd:Power:AVERAGE \
LINE3:Power#0000FF:"Average"

rrdtool graph /tmp/power-day.png \
--start end-1d --width 700 --end now --slope-mode \
--no-legend --vertical-label Watts --lower-limit 0 \
--alt-autoscale-max \
DEF:Power=/tmp/current_cost.rrd:Power:AVERAGE \
DEF:PowerMin=/tmp/current_cost.rrd:Power:MIN \
DEF:PowerMax=/tmp/current_cost.rrd:Power:MAX \
CDEF:PowerRange=PowerMax,PowerMin,- \
LINE1:PowerMin: \
AREA:PowerRange#0000FF11:"Error Range":STACK \
LINE1:PowerMin#0000FF33:"Min" \
LINE1:PowerMax#0000FF33:"Max" \
LINE1:Power#0000FF:"Average"

rrdtool graph /tmp/power-week.png \
--start end-7d --width 700 --end now --slope-mode \
--no-legend --vertical-label Watts --lower-limit 0 \
--alt-autoscale-max \
DEF:Power=/tmp/current_cost.rrd:Power:AVERAGE \
DEF:PowerMin=/tmp/current_cost.rrd:Power:MIN \
DEF:PowerMax=/tmp/current_cost.rrd:Power:MAX \
CDEF:PowerRange=PowerMax,PowerMin,- \
LINE1:PowerMin: \
AREA:PowerRange#0000FF11:"Error Range":STACK \
LINE1:PowerMin#0000FF33:"Min" \
LINE1:PowerMax#0000FF33:"Max" \
LINE1:Power#0000FF:"Average"