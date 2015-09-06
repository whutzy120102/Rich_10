## 更新自己 Fork 的代码项目和原作者的项目进度一致的方法 ##

 + 经过一段时间， 有可能作者原来的代码变化很大， 你想接着在他最新的代码上修改， 这时你需要合并原作者的最新代码过来， 让你的项目变成最新的。这个需要接着在你的本地操作。
假设前提是， 你现在你的代码是克隆下来在本地的。 例如我 Fork 了 whutzy120102/Rich_10 项目， 我的项目地址是 https://github.com/oneoneoneone/Rich_10.git 我现在克隆这个在我的本地了。


+  git clone https://github.com/oneoneoneone/Rich_10.git

接着， 我只需要添加 whutzy120102/Rich_10 项目的地址,也就是主项目的 remote 地址， 我们加入后， 给代码 fetch 过来， 然后进行 merge 的合并操作。


+ git remote add org https://github.com/whutzy120102/Rich_10.git
+ git fetch org
+ git merge org/master

这样就能给你的当前本地的项目变成和原作者的主项目一样。 然后你按正常的流程进行修改并提交到你的项目就好了。

+ git commit -am '更新到原作者的主分支的进度'
+ git push origin