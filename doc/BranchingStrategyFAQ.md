# Branching strategy

Before you start working on a particular piece of work, take the ID of the Story or Task if there is no story and using that create a branch for those changes.

<WORK_ID>_<SENSIBLE_DESCRIPTIVE_BRANCH_NAME>

I.E. I have a story: 

>[A291002-65](https://cseejira.essex.ac.uk/browse/A291002-65) Implement the piecewise linear regression in JAVA

I will pull the latest changes in master, I will create copy of master, feature branch called:

>A291002-65_piecewise_linear_regression_java

I will create my changes, add them, commit them and push the current branch to origin.

Now master doesn't yet contain my changes, but my feature branch has. I will go to Gitlab on the web, to the Merge Requests section and create a merge request from my feature branch to master. If anyone needs to see the changes, do a peer review, approve them, I would set them as the reviewers of that pull request.

After the peer reviews are done (if needed), someone completes that pull request and my changes would now become part of master.

# Basic GIT commands

## Get the latest changes

```git pull```

## Start tracking file/s

```git add ./fileName```

or for everything in the current path

```git add .```

everything under the current path

```git add *```

## Commit the tracked changes

```git commit -m "<TASK/STORY_ID> and a sensible description regarding these particular changes" -m "second message, which is optional, is used if you need to describe the changes in more detail"```

## Push the latest changes


```git push```


# Useful GIT commands for branching:

## Create a new branch

```git checkout -b <BRANCH_NAME>```
## List branches

```git branch```
## List branches including those in the origin

```git branc -r```
## Delete branch 

```git branch -D <BRANCH_NAME>```
## Switch branches

```git checkout <BRANCH_NAME>```
## Get a local copy of a branch residing in origin

```git checkout -b <LOCAL_BRAMCH_NAME> --track origin/<BRANCH_NAME>```
## Push a branch that is not yet on the origin

```git push```
It will rror out and tell you what to do next, you do not have to memorise this as long as you can copy/paste from the terminal. It will generally tell you to do something similar to this:

```git push --set-upstream origin <BRANCH_NAME>```
# Other useful GIT commands

## Show the commit history of the current branch

```git log```
## Show branches and their tracking information (what origin branches are connected to them)

```git branch -vv```