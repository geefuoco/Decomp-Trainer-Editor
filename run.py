#! /usr/bin/python3
import subprocess
import os


def run_jar():
    """
    Runs the jar file in the build directory
    """
    subprocess.run("./build.py")
    subprocess.run("java -jar ./build/decomp-trainer-editor.jar".split())


if __name__ == "__main__":
    assert os.path.exists("./build/decomp-trainer-editor.jar"), "Jar file not found."
    run_jar()
