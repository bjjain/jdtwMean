# jdtwMean

jdtwMean is a Java implementation of two algorithms for time series averaging in dynamic time warping spaces:

* DTW Barycenter Averaging (DBA)
* Stochastic Subgradient Method (SSG)

Both algorithms are described and discussed in [1]. 

### Requirements:
* Java 8 and higher
* No dependencies to other libraries


### Table of Contents 

* Quick Start
* Datasets
* Options
* Examples
* Reference
* Contact


### Quick Start 

This implementation assumes the file structure and data format of the [UCR time series repository](http://www.cs.ucr.edu/~eamonn/time_series_data/). The UCR file structure for a dataset with name `<data>` residing in directors `<dir>` is as follows: 

* `dir/data/data_TRAIN`
* `dir/data/data_TEST`

For averaging time series, this implementation merges the training and test set to a single set and then computes a sample mean. There are two ways two apply a mean algorithm: (1) via command line and (2) via class `app/DTWMean.java`.  

1. Command line: specify options and call

    `java -jar jdtwMean.jar <dir> <data> <options>`
    
The `<dir>` and `<data>` arguments are mandatory. Without setting further options, the SSG algorithm with default parameter setting is called. See section OPTIONS for a detailed description of how to set options.

2. Directly set arguments in class `app/DTWMean`, then compile and run class `DTWMean`. For setting the arguments, class `DTWMean` provides three attributes

* `String dir`
* `String data`
* `String opts`

The three attributes are set in the same way as the corresponding arguments in the command line. Options are set as a single string. For further details on how to set options, we refer to OPTIONS


### DATASETS

The folder `*./data/` contains the following datasets of the [UCR time series datasets](http://www.cs.ucr.edu/~eamonn/time_series_data/) provided by E. Keogh:

* CBF
* Coffee
* Beef

Each dataset consists of a directory containing a training and a test file dataset. 


### OPTIONS#1

Setting an option overwrites the corresponding default values. Therefore, only options need to be set that differ from the default. The following options are given:

**Options:**

```
-A  <string> : type of mean algorithm (default: SSG)
	       DBA -- DTW Barycenter Averaging
	       SSG -- Stochastic Subgradient Method
-T  <int>    : maximum number of epochs (default: 50)
-t  <int>    : maximum number of epochs without improvement (default: 5)
-l0 <double> : initial learning rate (default: 0.1)
-l1 <double> : final learning rate (default: 0.001)
-o <int>     : output mode (default: 2)
               0 -- quiet mode, prints no progress information
               1 -- prints a dot after every epoch
               2 -- reports progress info after every epoch	 
```


### Examples 

In the following examples `<dir>` refers to the directory containing the UCR dataset. 

1. `java -jar dtwmean.jar <dir> Coffee` 

Applies the stochastic subgradient method with default parameter setting to the Coffee dataset. The parameters are:

```
	-T  50		terminates latest after 50 epochs
	-t   5		prematurely terminates after 5 epochs without improvement
	-l0  0.1	initial learning rate	
	-l1  0.001	final learning rate
	-o   2		reports progress info after every epoch
```

2. `java -jar dtwmean.jar <dir> Coffee -A DBA -o 0`

Applies the DBA algorithm to the Coffee dataset without providing progress information after every epoch. The parameters are:

```
	-T  50		terminates latest after 50 epochs
	-t   5		prematurely terminates after 5 epochs without improvement
	-o   0		quite mode
```

3. `java -jar dtwmean.jar <dir> Coffee -A SSG -T 100 -t 30`

Applies the stochastic subgradient method to the Coffee dataset. The algorithm prematurely terminates after 30 epochs without improvement or latest after 100 epochs. 


### References 

If you find this library helpful, please cite it as

[1] David Schultz and Brijnesh Jain. Nonsmooth Analysis and Stochastic Subgradient Methods for the Sample Mean Problem in Dynamic Time Warping Spaces, Pattern Recognition, 74:340-358, 2018. ([arXiv preprint](https://arxiv.org/abs/1701.06393))

If you use any of the UCR time series datasets provided with this release, please consult the website

http://www.cs.ucr.edu/~eamonn/time_series_data/

and agree to the terms of use.


### Contact 

For any questions and comments, please email brijnesh.jain(at)gmail.com


