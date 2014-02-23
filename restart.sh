#!/bin/bash

kill -9 `ps aux | grep currentcost | grep -v grep | sed 's/\s\+/ /' | cut -d' ' -f 2`

service current-cost restart &

