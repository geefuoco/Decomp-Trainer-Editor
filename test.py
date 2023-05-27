#! /usr/bin/python3
import subprocess
import os


def run_test(path: str):
    """
    Run the test for java program

    Parameters
    ----------
    path: str
        Path to main function file
    """
    os.chdir("./build")
    subprocess.run(["java", path])


if __name__ == "__main__":
    # build before running
    subprocess.run("./build.py")
    run_test("me.disturbo.main.TestMain")
