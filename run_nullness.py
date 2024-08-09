'''
This script runs checker-framework nullness checker in the source folder.
'''
import os

BENCHMARKS_FOLDER = f"source"
RESULTS_FOLDER = f"nullness"
COMPILED_CLASSES_FOLDER = "classes"
SRC_FILES = "srcs.txt"
CF_BINARY = "tools/cf/checker/bin/javac"
CHECKER_DIR = "tools/cf"
CF_COMMAND = "-processor org.checkerframework.checker.nullness.NullnessChecker"
TIMEOUT = 1800
TIMEOUT_CMD = "timeout"

#create the output folder if it doesn't exist
if not os.path.exists(RESULTS_FOLDER):
    os.mkdir(RESULTS_FOLDER)

#Loop through the benchmarks
print("Completed Benchmarks")
for benchmark in os.listdir(BENCHMARKS_FOLDER):

    #skip non-directories
    if not os.path.isdir(f'{BENCHMARKS_FOLDER}/{benchmark}'):
        continue
    
    #create a folder for the compiled classes if it doesn't exist
    if not os.path.exists(COMPILED_CLASSES_FOLDER):
        os.mkdir(COMPILED_CLASSES_FOLDER)
    
    print("working on: " + benchmark) 

    #Get a list of Java source code files.
    find_srcs_command = f'find {BENCHMARKS_FOLDER}/{benchmark}/src -name "*.java" > {SRC_FILES}'
    os.system(find_srcs_command)

    #get folder with libraries used by benchmark
    lib_folder = f'source/{benchmark}/lib:{CHECKER_DIR}/checker/dist/checker-qual.jar'

    #execute infer on the source files
    command = (TIMEOUT_CMD 
        + " " + str(TIMEOUT)
        + " " + CF_BINARY
        + " " + CF_COMMAND
        + " " + "-AassumePure"
        + " " + "-Adetailedmsgtext"
        # comment to exclude stubs
        + " " + "-Aajava=" + "stubs/{}".format(benchmark)
        + " " + "-d"
        + " " + COMPILED_CLASSES_FOLDER
        + " " + "-Xmaxerrs 100000" 
        + " " + "-J-Xmx32G"
        + " -cp " + lib_folder 
        + " @" + SRC_FILES
        + " 2> " +  RESULTS_FOLDER
        + "/" + benchmark + ".txt"
    )
    print(command)
    os.system(command)

    
print("All benchmarks completed")
