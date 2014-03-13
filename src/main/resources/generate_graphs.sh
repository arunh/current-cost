#!/bin/bash

CC_DIR=/opt/current-cost
DB_FILE=$CC_DIR/db/current-cost.rrd
IMG_DIR=$CC_DIR/html
NEAR_TERM_UPPER_LIMIT=2500

#Use different limit for 6mth+ due to averaging, allows patterns to be seen
LONG_TERM_UPPER_LIMIT=1250

rrdtool graph $IMG_DIR/cc-15minutes.png \
--start end-15minutes --width 700 --end now --slope-mode \
--no-legend --vertical-label Watts --lower-limit 0 \
--upper-limit=$NEAR_TERM_UPPER_LIMIT --rigid \
DEF:Power=$DB_FILE:Power:AVERAGE \
DEF:PowerMin=$DB_FILE:Power:MIN \
DEF:PowerMax=$DB_FILE:Power:MAX \
CDEF:PowerRange=PowerMax,PowerMin,- \
LINE1:PowerMin: \
AREA:PowerRange#0000FF11:"Error Range":STACK \
LINE1:PowerMin#0000FF33:"Min" \
LINE1:PowerMax#0000FF33:"Max" \
LINE1:Power#0000FF:"Average"

rrdtool graph $IMG_DIR/cc-1hour.png \
--start end-1hour --width 700 --end now --slope-mode \
--no-legend --vertical-label Watts --lower-limit 0 \
--upper-limit=$NEAR_TERM_UPPER_LIMIT --rigid \
DEF:Power=$DB_FILE:Power:AVERAGE \
DEF:PowerMin=$DB_FILE:Power:MIN \
DEF:PowerMax=$DB_FILE:Power:MAX \
CDEF:PowerRange=PowerMax,PowerMin,- \
LINE1:PowerMin: \
AREA:PowerRange#0000FF11:"Error Range":STACK \
LINE1:PowerMin#0000FF33:"Min" \
LINE1:PowerMax#0000FF33:"Max" \
LINE1:Power#0000FF:"Average"

rrdtool graph $IMG_DIR/cc-4hours.png \
--start end-4hours --width 700 --end now --slope-mode \
--no-legend --vertical-label Watts --lower-limit 0 \
--upper-limit=$NEAR_TERM_UPPER_LIMIT --rigid \
DEF:Power=$DB_FILE:Power:AVERAGE \
DEF:PowerMin=$DB_FILE:Power:MIN \
DEF:PowerMax=$DB_FILE:Power:MAX \
CDEF:PowerRange=PowerMax,PowerMin,- \
LINE1:PowerMin: \
AREA:PowerRange#0000FF11:"Error Range":STACK \
LINE1:PowerMin#0000FF33:"Min" \
LINE1:PowerMax#0000FF33:"Max" \
LINE1:Power#0000FF:"Average"

rrdtool graph $IMG_DIR/cc-1day.png \
--start end-1day --width 700 --end now --slope-mode \
--no-legend --vertical-label Watts --lower-limit 0 \
--upper-limit=$NEAR_TERM_UPPER_LIMIT --rigid \
DEF:Power=$DB_FILE:Power:AVERAGE \
DEF:PowerMin=$DB_FILE:Power:MIN \
DEF:PowerMax=$DB_FILE:Power:MAX \
CDEF:PowerRange=PowerMax,PowerMin,- \
LINE1:PowerMin: \
AREA:PowerRange#0000FF11:"Error Range":STACK \
LINE1:PowerMin#0000FF33:"Min" \
LINE1:PowerMax#0000FF33:"Max" \
LINE1:Power#0000FF:"Average"

rrdtool graph $IMG_DIR/cc-1week.png \
--start end-1week --width 700 --end now --slope-mode \
--no-legend --vertical-label Watts --lower-limit 0 \
--upper-limit=$NEAR_TERM_UPPER_LIMIT --rigid \
DEF:Power=$DB_FILE:Power:AVERAGE \
DEF:PowerMin=$DB_FILE:Power:MIN \
DEF:PowerMax=$DB_FILE:Power:MAX \
CDEF:PowerRange=PowerMax,PowerMin,- \
LINE1:PowerMin: \
AREA:PowerRange#0000FF11:"Error Range":STACK \
LINE1:PowerMin#0000FF33:"Min" \
LINE1:PowerMax#0000FF33:"Max" \
LINE1:Power#0000FF:"Average"

rrdtool graph $IMG_DIR/cc-1month.png \
--start end-1month --width 700 --end now --slope-mode \
--no-legend --vertical-label Watts --lower-limit 0 \
--upper-limit=$NEAR_TERM_UPPER_LIMIT --rigid \
DEF:Power=$DB_FILE:Power:AVERAGE \
DEF:PowerMin=$DB_FILE:Power:MIN \
DEF:PowerMax=$DB_FILE:Power:MAX \
CDEF:PowerRange=PowerMax,PowerMin,- \
LINE1:PowerMin: \
AREA:PowerRange#0000FF11:"Error Range":STACK \
LINE1:PowerMin#0000FF33:"Min" \
LINE1:PowerMax#0000FF33:"Max" \
LINE1:Power#0000FF:"Average"

rrdtool graph $IMG_DIR/cc-6months.png \
--start end-6months --width 700 --end now --slope-mode \
--no-legend --vertical-label Watts --lower-limit 0 \
--upper-limit=$LONG_TERM_UPPER_LIMIT --rigid \
DEF:Power=$DB_FILE:Power:AVERAGE \
DEF:PowerMin=$DB_FILE:Power:MIN \
DEF:PowerMax=$DB_FILE:Power:MAX \
CDEF:PowerRange=PowerMax,PowerMin,- \
LINE1:PowerMin: \
AREA:PowerRange#0000FF11:"Error Range":STACK \
LINE1:PowerMin#0000FF33:"Min" \
LINE1:PowerMax#0000FF33:"Max" \
LINE1:Power#0000FF:"Average"

rrdtool graph $IMG_DIR/cc-1year.png \
--start end-1year --width 700 --end now --slope-mode \
--no-legend --vertical-label Watts --lower-limit 0 \
--upper-limit=$LONG_TERM_UPPER_LIMIT --rigid \
DEF:Power=$DB_FILE:Power:AVERAGE \
DEF:PowerMin=$DB_FILE:Power:MIN \
DEF:PowerMax=$DB_FILE:Power:MAX \
CDEF:PowerRange=PowerMax,PowerMin,- \
LINE1:PowerMin: \
AREA:PowerRange#0000FF11:"Error Range":STACK \
LINE1:PowerMin#0000FF33:"Min" \
LINE1:PowerMax#0000FF33:"Max" \
LINE1:Power#0000FF:"Average"

