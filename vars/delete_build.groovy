import jenkins.model.*
import hudson.model.*
import com.cloudbees.hudson.plugins.folder.*
import jenkins.branch.*
import org.jenkinsci.plugins.workflow.job.*
import org.jenkinsci.plugins.workflow.multibranch.*


def deleteOldBuilds(item, Integer numberOfBuildsToKeep, Integer numberOfSuccessfulBuildsKept) {
    def count = 1

    println('Checking for Old Builds...')

    for (build in item.getBuilds()) {
        if(count++ >= numberOfBuildsToKeep) {
            if(item.getBuildStatusIconClassName() == 'icon-blue' && numberOfSuccessfulBuildsKept == 0) {
                println('Keep ' + build)
            } else {
                println('Deleting ' + build)
                build.delete()
            }
        } else if(item.getBuildStatusIconClassName() == 'icon-blue') {
            numberOfSuccessfulBuildsKept++
        }
    }
    println('PRIOR BUILD COUNT: (' + count + ')')
    println ''
}
