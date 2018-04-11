class Renamer < Formula
  desc 'Example of a webapp desktop integration'
  homepage 'https://github.com/ajavageek/renamer'
  url 'https://github.com/ajavageek/renamer/releases/download/renamer-0.0.1-SNAPSHOT/renamer-0.0.1-SNAPSHOT.tar.gz'
  sha256 '4e9dab43b271ed640c422b3d5abb8686501fb011c6f3e4e8c99b57f6b67fea68'

  bottle :unneeded
  depends_on :java => '1.8+'

  def install
    libexec.install Dir['*']
    bin.write_jar_script libexec/'renamer-app-0.0.1-SNAPSHOT.jar', 'renamer'
  end
end
