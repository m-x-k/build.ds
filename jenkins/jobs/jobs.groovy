job('job0') {
    steps {
        shell('echo "Job0!"')
    }
}
job('job1') {
    steps {
        shell('echo "Job1!"')
    }
}
job('job2') {
    steps {
        shell('echo "Job2!"')
    }
}

