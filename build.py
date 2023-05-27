#! /usr/bin/python3
import subprocess
import os
import shutil

def compile_java_project() -> bool:
    """
    Compile the java project into the build directory of the current 

    Parameters
    ----------
    path: str
        Path to the java file which holds the main function

    Exceptions
    ----------
    AssertionError
    """

    assert os.path.exists("./src"), "Not in the root directory of the project. Could not find src folder"
    assert os.path.exists("./build"), "./build directory does not exist"
    
    run_str = "find -name '*.java' > sources.txt"

    try:
        shutil.rmtree("./build")
        os.mkdir("./build")
        subprocess.run([run_str], shell=True)
        subprocess.run([
            "javac",
            "-d",
            "./build",
            "@sources.txt"
        ])

        print("Java project compiled")
        return True
    except Exception as e:
        print("An error has occurred. Process could not run.")
        print(str(e))
        return False

def compile_project_into_jar(path: str, jar_name: str) -> bool:
    """
    Compiles a java project into a jar file

    Parameters
    ----------
    path: str
        Path to the manifest file
    jar_name: str
        Name of the jar file

    Exceptions
    ----------
    AssertionError
    """
    assert os.path.exists("./build"), "./build directory does not exist"

    try:
        os.chdir("./build")
        assert os.path.exists(path), f"Manifest file could not be found at: {path}"
        subprocess.run([
            "jar",
            "cmf",
            path,
            jar_name,
            "."
        ])
        return True
    except Exception as e:
        print("An error has occured.")
        print(str(e))
        return False


if __name__ == "__main__":
    try:
        if compile_java_project():
            compile_project_into_jar("../src/META-INF/MANIFEST.MF", "decomp-trainer-editor.jar")
        else:
            print("Compiling project failed.")
    except Exception as e:
        print(str(e))
