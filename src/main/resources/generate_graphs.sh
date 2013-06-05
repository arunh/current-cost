#!/bin/bash

CC_DIR=/opt/current-cost
DB_FILE=$CC_DIR/db/current-cost.rrd
IMG_DIR=$CC_DIR/graphs

rrdtool graph $IMG_DIR/cc-10min.png \
--start end-10m --width 700 --end now --slope-mode \
--no-legend --vertical-label Watts --lower-limit 0 \
--alt-autoscale-max \
DEF:Power=$DB_FILE:Power:AVERAGE \
LINE3:Power#0000FF:"Average"

rrdtool graph $IMG_DIR/cc-1day.png \
--start end-1d --width 700 --end now --slope-mode \
--no-legend --vertical-label Watts --lower-limit 0 \
--alt-autoscale-max \
DEF:Power=$DB_FILE:Power:AVERAGE \
DEF:PowerMin=$DB_FILE:Power:MIN \
DEF:PowerMax=$DB_FILE:Power:MAX \
CDEF:PowerRange=PowerMax,PowerMin,- \
LINE1:PowerMin: \
AREA:PowerRange#0000FF11:"Error Range":STACK \
LINE1:PowerMin#0000FF33:"Min" \
LINE1:PowerMax#0000FF33:"Max" \
LINE1:Power#0000FF:"Average"

rrdtool graph $IMG_DIR/cc-7day.png \
--start end-7d --width 700 --end now --slope-mode \
--no-legend --vertical-label Watts --lower-limit 0 \
--alt-autoscale-max \
DEF:Power=$DB_FILE:Power:AVERAGE \
DEF:PowerMin=$DB_FILE:Power:MIN \
DEF:PowerMax=$DB_FILE:Power:MAX \
CDEF:PowerRange=PowerMax,PowerMin,- \
LINE1:PowerMin: \
AREA:PowerRange#0000FF11:"Error Range":STACK \
LINE1:PowerMin#0000FF33:"Min" \
LINE1:PowerMax#0000FF33:"Max" \
LINE1:Power#0000FF:"Average"

