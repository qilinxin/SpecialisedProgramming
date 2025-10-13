public class RockStar {

  public static int getNumSongs(int ff, int fs, int sf, int ss) {
    if (ff == 0 && fs == 0) {
      return ss + (sf > 0 ? 1 : 0);
    }

    int ans = ff;

    if (fs == 0) {
      return ans;
    }

    ans += 1;

    ans += ss;

    int fsRemain = fs - 1;

    ans += 2 * Math.min(sf, fsRemain);
    if (sf > fsRemain) ans += 1;

    return ans;
  }

  public static void main(String[] args) {
    int ff = 192, fs = 279, sf = 971, ss = 249;
    int res = getNumSongs(ff, fs, ss, sf);
    System.out.println(res);

  }
}
